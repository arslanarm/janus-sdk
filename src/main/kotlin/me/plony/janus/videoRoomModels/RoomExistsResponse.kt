package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class RoomExistsResponse(
    val videoroom: String,
    val room: Long,
    val exists: Boolean
)