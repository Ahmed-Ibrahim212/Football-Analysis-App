package com.example.footballdataapp.data

data class SingleTeams(
    val activeCompetitions: List<ActiveCompetition>,
    val address: String,
    val area: AreaXX,
    val clubColors: String,
    val crestUrl: String,
    val email: String,
    val founded: Int,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val phone: String,
    val shortName: String,
    val squad: List<Squad>,
    val tla: String,
    val venue: String,
    val website: String
)