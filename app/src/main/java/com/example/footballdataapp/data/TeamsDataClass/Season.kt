package com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish

data class Season(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Any
)