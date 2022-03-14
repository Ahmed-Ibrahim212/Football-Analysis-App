package com.example.footballdataapp.data.TeamsDataClass

import com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish.Area

data class Competition(
    val area: Area,
    val code: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String
)