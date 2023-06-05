package me.plony.janus

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import me.plony.janus.models.*
import kotlin.properties.Delegates

class Janus(val baseUrl: String, val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {
    companion object {
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = false
        }
    }
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(json)
        }
        install(WebSockets)
        engine {
            requestTimeout = Long.MAX_VALUE
        }
    }
    var sessionId by Delegates.notNull<Long>()
    val events = MutableSharedFlow<JanusEvent<JsonElement>>()
    val gpsFlow = MutableSharedFlow<GPS>()
    lateinit var getEventsJob: Job
    lateinit var gpsJob: Job
    suspend fun initializeSession() {
        val response = client.post(baseUrl) {
            contentType(ContentType.Application.Json)
            setBody(JanusObject("create"))
        }
        sessionId = response.body<JanusData<Id>>().data.id
        getEventsJob = scope.launch {
            while (true) {
                getEvents()?.let { events.emitAll(it.asFlow()) }
            }
        }

        gpsJob = scope.launch {
            client.webSocket("http://212.192.9.218/uploader") {
                val id = json.decodeFromString<Identifier>((incoming.receive() as Frame.Text).readText())
                println(id)
                gpsFlow.collectLatest {
                    outgoing.send(Frame.Text(json.encodeToString(it)))
                }
            }
        }
    }
    suspend fun getEvents(): List<JanusEvent<JsonElement>>? {
        val response = client.get("$baseUrl/$sessionId?maxev=5")
        return try {
            response.body<List<JanusEvent<JsonElement>>>()
        } catch (e: JsonConvertException) {
            response.body<List<JanusKeepAlive>>()
            null
        }
    }
    suspend fun destroy() {
        client.post("$baseUrl/$sessionId") {
            contentType(ContentType.Application.Json)
            setBody(JanusObject("destroy"))
        }
    }
    suspend fun attach(pluginName: String): JanusPlugin {
        val response = client.post("$baseUrl/$sessionId") {
            contentType(ContentType.Application.Json)
            setBody(
                JanusPluginAttach(
                    "attach",
                    pluginName
                )
            )
        }
        val pluginHandleId = response.body<JanusData<Id>>().data.id
        return JanusPlugin(pluginHandleId, this)
    }

    suspend fun emitGps(gps: GPS) {
        gpsFlow.emit(gps)
    }
}