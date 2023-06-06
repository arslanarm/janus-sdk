package me.plony.janus.videoRoomModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val request: String,
    @SerialName("h264_profile")
    val h264Profile: String? = null
)
