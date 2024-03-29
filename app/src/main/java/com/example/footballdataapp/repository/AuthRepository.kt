package com.example.footballdataapp.repository

import com.example.footballdataapp.data.Competition
import com.example.footballdataapp.data.CompetitionDataClass
import com.example.footballdataapp.data.local.TeamsDatabase
import com.example.footballdataapp.network.FootballApi
import retrofit2.Response
import com.example.footballdataapp.data.SingleTeams
import javax.inject.Inject


class AuthRepository@Inject constructor(private val api: FootballApi, private val teamsDatabase: TeamsDatabase) {

    suspend fun getMatches(token:String): Response<CompetitionDataClass> {
       return api.getFootball(token)
    }
    suspend fun getTeamsImage(token: String,leagueId:Int): Response<com.example.footballdataapp.data.TeamsDataClass.SingleTeams>{
        return api.getTeams(token,leagueId)
    }

    suspend fun insertImage(competition: Competition) = teamsDatabase.getTeamsDao().insert(competition)

    fun getCompetitionsData(): List<Competition> = teamsDatabase.getTeamsDao().getCompetitons()

    suspend fun getTeams(token:String, teamId: Int): Response<SingleTeams> {
        return api.getTeamsDetails(token, teamId)
    }
}