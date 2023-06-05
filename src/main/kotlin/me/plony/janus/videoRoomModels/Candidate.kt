package me.plony.janus.videoRoomModels

import kotlinx.serialization.Serializable

@Serializable
data class Candidate(
    val sdpMid: String,
    val sdpMLineIndex: Int,
    val candidate: String
)
