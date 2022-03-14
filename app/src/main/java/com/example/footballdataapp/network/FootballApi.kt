package com.example.footballdataapp.network

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.footballdataapp.data.CompetitionDataClass
import com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FootballApi {
    @GET("competitions")
    suspend fun getFootball(
        @Header("X-Auth_Token") token:String
    ):Response<CompetitionDataClass>

    @GET("competitions/{leagueId}/teams")
    suspend fun getTeams(
        @Header("X-Auth-Token") token: String,
        @Path("leagueId") leagueId:Int
    ):Response<Teams>

    @GET("teams/674")
    suspend fun getTeamsDetails(
        @Header("X-Auth-Token") token: String,
    ):Response<Teams>
}

