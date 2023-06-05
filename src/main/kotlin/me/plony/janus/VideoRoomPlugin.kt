package me.plony.janus

import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import me.plony.janus.models.JanusEvent
import me.plony.janus.models.Jsep
import me.plony.janus.videoRoomModels.*

class VideoRoomPlugin(val pluginHandle: JanusPlugin) {
    val joinedEvent = pluginHandle.janus.events
        .filter { it.pluginData.data.jsonObject["videoroom"]?.jsonPrimitive?.content == "joined" }
        .map { Janus.json.decodeFromJsonElement<JoinedEvent>(it.pluginData.data) }
    val answerEvent = pluginHandle.janus.events
        .filter { it.jsep != null && it.jsep.type == "answer" }
        .map { it.jsep!! }

    suspend fun createRoom(): Long {
        val response = pluginHandle.sendMessage(Request("create"))
        val room = response.body<JanusEvent<RoomResponse>>().pluginData.data
        return room.room
    }

    suspend fun destroyRoom(id: Long) {
        pluginHandle.sendMessage(RoomRequest("destroy", id))
    }
    suspend fun roomExists(id: Long) =
        pluginHandle.sendMessage(RoomRequest("exists", id))
            .body<RoomExistsResponse>()
            .exists

    suspend fun join(roomId: Long, joinAs: JoinAs, displayName: String) {
        pluginHandle.sendMessage(
            JoinRequest(
            "join",
            when (joinAs) {
                JoinAs.Publisher -> "publisher"
                JoinAs.Subscriber -> "subscriber"
            },
            roomId,
            displayName
        )
        )
    }
    suspend fun publish(record: Boolean = false, jsep: Jsep) {
        pluginHandle.sendMessage(PublishRequest("publish", record), jsep).also { println(it.bodyAsText()) }
    }
    suspend fun unpublish() {
        pluginHandle.sendMessage(Request("unpublish"))
    }
}