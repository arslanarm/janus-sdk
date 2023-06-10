package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class RoomList(
    val videoroom: String,
    val list: List<Room>
) {
    @Serializable
    data class Room(
        val room: Long
    )
}
