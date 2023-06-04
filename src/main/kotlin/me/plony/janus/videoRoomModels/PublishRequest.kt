package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class PublishRequest(
    val request: String,
    val record: Boolean
)