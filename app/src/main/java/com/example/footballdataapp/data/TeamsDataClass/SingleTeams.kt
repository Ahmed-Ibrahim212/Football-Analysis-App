package com.example.footballdataapp.data.TeamsDataClass

import com.example.footballdataapp.data.Filters

data class SingleTeams(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val season: Season,
    val teams: List<Team>
)