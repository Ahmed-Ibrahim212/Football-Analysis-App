package com.example.footballdataapp.data.local

import androidx.room.TypeConverter
import com.example.footballdataapp.data.Area
import com.example.footballdataapp.data.CurrentSeason

class Converters {

    @TypeConverter
    fun fromCurrentSeason(currentSeason: CurrentSeason):String{
        return currentSeason.startDate.toString()
    }
    @TypeConverter
    fun toCurrentSeason(startDate:String):CurrentSeason{
        return CurrentSeason(0,startDate,0,startDate,startDate)
    }

    @TypeConverter
    fun fromArea(area: Area): String {
        return area.name
    }

    @TypeConverter
    fun toArea(name:String):Area{
        return Area(name,name,0,name)
    }
}