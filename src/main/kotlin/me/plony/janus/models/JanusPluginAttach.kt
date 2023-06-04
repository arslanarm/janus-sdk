package me.plony.janus.models

import kotlinx.serialization.Serializable

@Serializable
data class JanusPluginAttach(
    val janus: String,
    val plugin: String,
    val transaction: String = randomTransaction()
)
