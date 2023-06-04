package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class RoomResponse(val videoroom: String, val room: Long, val permanent: Boolean = false)