package com.example.footballdataapp.network

import com.example.footballdataapp.data.CompetitionDataClass
import com.example.footballdataapp.data.TeamsDataClass.Teams
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

    @GET("teams")
    suspend fun getTeamsDetails(
        @Header("X-Auth-Token") token: String,
        @Path ("teamId") teamId: Int
    ):Response<Teams>
}

