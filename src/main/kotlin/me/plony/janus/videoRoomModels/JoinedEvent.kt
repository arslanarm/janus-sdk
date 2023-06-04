package me.plony.janus.videoRoomModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JoinedEvent(
    val videoroom: String,
    val room: Long,
    val description: String? = null,
    val id: Long,
    @SerialName("private_id")
    val privateId: Long,
    val publishers: List<Publisher>,
    val attendees: List<Attendee> = listOf()
)

@Serializable
data class Attendee(
    val id: Long,
    val display: String? = null
)

@Serializable
data class Publisher(
    val id: Long,
    val display: String? = null,
    val dummy: Boolean,
    val streams: List<Stream>
)

@Serializable
data class Stream(
    val type: StreamType,
    val mindex: String,
    val mid: String,
    val disabled: Boolean, // if disabled then following properties will be null
    val coded: String? = null,
    val description: String? = null,
    val moderated: Boolean? = null,
    val simulcast: Boolean? = null,
    val svc: Boolean? = null,
    val talking: Boolean? = null
)

enum class StreamType {
    @SerialName("audio") Audio,
    @SerialName("video") Video,
    @SerialName("data") Data
}

