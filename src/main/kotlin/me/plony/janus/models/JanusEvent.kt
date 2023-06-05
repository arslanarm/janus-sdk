package me.plony.janus.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JanusEvent<T>(
    val janus: String,
    val sender: Long,
    val transaction: String,
    @SerialName("plugindata")
    val pluginData: PluginData<T>,
    val jsep: Jsep? = null
) {
    @Serializable
    data class PluginData<T>(
        val plugin: String,
        val data: T
    )
}

@Serializable
data class JanusKeepAlive(
    val janus: String
)

@Serializable
data class TrickleEvent(
    val janus: String,
    val transaction: String,
    val candidate: Candidate
) {
    @Serializable
    data class Candidate(val completed: Boolean)
}