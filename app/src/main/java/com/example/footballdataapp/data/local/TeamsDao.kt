package com.example.footballdataapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.footballdataapp.data.Competition
import com.example.footballdataapp.data.CompetitionDataClass
import com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish.Team
import com.olamachia.simpleblogappwithdatabinding.models.dataclasses.Fish.Teams


@Dao
interface TeamsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    // upsert does update if post exists or insert new
    suspend fun insert(competition: Competition)


    @Query("SELECT * FROM 'teamsImage' WHERE id >= 2003")
    fun getCompetitons(): List<Competition>

}