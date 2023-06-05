package me.plony.janus.models

import kotlinx.serialization.Serializable

@Serializable
data class Identifier(
    val id: String
)


@Serializable
data class GPS(
    val longitude: Double,
    val latitude: Double
)