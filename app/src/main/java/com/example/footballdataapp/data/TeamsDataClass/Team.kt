package com.example.footballdataapp.data.TeamsDataClass

import com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish.AreaX

data class Team(
    val address: String,
    val area: AreaX,
    val clubColors: String,
    val crestUrl: String,
    val email: String,
    val founded: Int,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val phone: String,
    val shortName: String,
    val tla: String,
    val venue: String,
    val website: String
)