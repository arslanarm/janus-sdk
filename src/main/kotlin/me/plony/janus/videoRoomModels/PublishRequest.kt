package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class PublishRequest(
    val request: String,
    val audiocodec: String? = null,
    val videocodec: String? = null,
    val record: Boolean
)