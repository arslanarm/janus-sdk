package me.plony.janus.models

import kotlinx.serialization.Serializable

@Serializable
data class JanusBody<T>(
    val janus: String,
    val transaction: String = randomTransaction(),
    val body: T,
    val jsep: Jsep? = null
)

@Serializable
data class Jsep(
    val type: String,
    val sdp: String
)
