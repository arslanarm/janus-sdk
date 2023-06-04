package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class JoinRequest(
    val request: String,
    val ptype: String,
    val room: Long,
    val displayName: String
)

enum class JoinAs {
    Publisher,
    Subscriber
}
