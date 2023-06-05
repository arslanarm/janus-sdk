package me.plony.janus.models

import kotlinx.serialization.Serializable
import me.plony.janus.videoRoomModels.Candidate

@Serializable
class JanusTrickleSingle(
    val janus: String,
    val transaction: String = randomTransaction(),
    val candidate: Candidate
)