package com.example.footballdataapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballdataapp.data.Competition

@Database(
    entities = [Competition::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    Converters::class
)
abstract class TeamsDatabase : RoomDatabase() {

    abstract fun getTeamsDao(): TeamsDao

    companion object {
        var DATABASE_NAME: String = "teams_db"
    }
}
