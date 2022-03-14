package com.example.footballdataapp.data

data class CurrentSeason(
    val currentMatchday: Int?,
    val endDate: String?="null",
    val id: Int,
    val startDate: String?="null",
    val winner: Any
)