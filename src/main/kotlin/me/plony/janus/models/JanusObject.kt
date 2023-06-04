package me.plony.janus.models

import kotlinx.serialization.Serializable

@Serializable
data class JanusObject(
    val janus: String,
    val transaction: String = randomTransaction()
)

fun randomTransaction(number: Int = 10): String {
    val alphabeticCapital = ('A'..'Z').toList()
    val alphabetic = ('a'..'z').toList()
    val numeric = ('0'..'9').toList()
    val chars = alphabeticCapital + alphabetic + numeric
    return (0 until number).map { chars.random() }.joinToString("")
}
