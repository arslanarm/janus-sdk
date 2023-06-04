package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class RoomRequest(
    val request: String,
    val room: Long,
)