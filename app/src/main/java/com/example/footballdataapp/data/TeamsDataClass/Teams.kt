package com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish

import com.example.footballdataapp.data.TeamsDataClass.Competition


data class Teams(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val season: Season,
    val teams: List<Team>
)