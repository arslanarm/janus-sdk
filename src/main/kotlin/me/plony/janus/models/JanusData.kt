package me.plony.janus.models

import kotlinx.serialization.Serializable

@Serializable
data class JanusData<T>(
    val janus: String,
    val transaction: String,
    val data: T
)
