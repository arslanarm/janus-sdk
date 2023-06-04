package me.plony.janus

import io.ktor.client.request.*
import io.ktor.http.*
import me.plony.janus.models.JanusBody
import me.plony.janus.models.JanusObject
import me.plony.janus.models.Jsep

class JanusPlugin(val pluginId: Long, val janus: Janus) {
    val url = "${janus.baseUrl}/${janus.sessionId}/$pluginId"

    suspend inline fun <reified T> sendMessage(data: T, jsep: Jsep? = null) =
        janus.client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(JanusBody("message", body = data, jsep = jsep))
        }


    suspend fun detach() {
        try {
            hangup()
        } finally {
            janus.client.post(url) {
                contentType(ContentType.Application.Json)
                setBody(JanusObject("detach"))
            }
        }
    }
    suspend fun hangup() {
        janus.client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(JanusObject("hangup"))
        }
    }
}