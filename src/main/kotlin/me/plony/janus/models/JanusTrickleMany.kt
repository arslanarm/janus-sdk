package me.plony.janus.models

import me.plony.janus.videoRoomModels.Candidate

data class JanusTrickleMany(
    val janus: String,
    val transaction: String = randomTransaction(),
    val candidates: List<Candidate>
)
