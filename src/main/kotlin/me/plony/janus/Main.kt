//package me.plony.janus
//
//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.plugins.websocket.*
//import io.ktor.serialization.kotlinx.json.*
//import io.ktor.websocket.*
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//import kotlinx.coroutines.launch
//import me.plony.janus.models.Jsep
//import me.plony.janus.videoRoomModels.Candidate
//import me.plony.janus.videoRoomModels.JoinAs
//import kotlin.time.Duration.Companion.minutes
//import kotlin.time.Duration.Companion.seconds
//
//suspend fun main(args: Array<String>) {
////    val janus = Janus("http://212.192.9.218:8088/janus")
////    janus.initializeSession() // инициализации сессии
////
////    janus.events
////        .onEach {
////            println(it)
////        }.launchIn(janus.scope)
////
////    val plugin = VideoRoomPlugin(janus.attach("janus.plugin.videoroom"))
////    plugin.list().forEach { plugin.destroyRoom(it.room.also { println(it) } ) }
//////    plugin.destroyRoom(roomId)
////    janus.destroy()
//    coroutineScope {
//        HttpClient(CIO) {
//            install(ContentNegotiation) {
//                json(Janus.json)
//            }
//            install(WebSockets)
//        }.run {
//            val identifier = "plony"
//            val i = launch {
//                webSocket("ws://localhost:1234/upload") {
//                    outgoing.send(Frame.Text(identifier))
//                    while (true) {
//                        outgoing.send(Frame.Text("""
//                            {
//                                "type": "gps",
//                                "latitude": 0.0,
//                                "longitude": 0.0
//                            }
//                        """.trimIndent()))
//                        delay(1.seconds)
//                        if ((0 until 100).random() < 50) {
//                            outgoing.send(Frame.Text("""
//                                {
//                                    "type": "emergency"
//                                }
//                            """.trimIndent()))
//                        }
//                    }
//                }
//            }
//            val j = launch {
//                webSocket("ws://localhost:1234/incoming") {
//                    delay(1000)
//                    outgoing.send(Frame.Text("""
//                        {
//                            "type": "subscribe",
//                            "users": ["$identifier"]
//                        }
//                    """.trimIndent()))
//                    for (frame in incoming) {
//                        if (frame is Frame.Text)
//                            println(frame.readText())
//                    }
//                }
//            }
//            i.join()
//            j.join()
//        }
//    }
//}