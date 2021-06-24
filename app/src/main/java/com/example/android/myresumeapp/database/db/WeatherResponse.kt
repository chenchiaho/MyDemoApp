package com.example.android.myresumeapp.database.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.myresumeapp.database.db.Main
import com.example.android.myresumeapp.database.entity.*
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(
        tableName = "current_weather"
)
data class WeatherResponse(
        @PrimaryKey(autoGenerate = false)
        var id: Int,
//        @Embedded(prefix = "main_")
//        var main: Main,
        var name: String,
//        @Embedded(prefix = "rain_")
//        var rain: Rain,
//        @Embedded(prefix = "sys_")
//        var sys: Sys,
//        var visibility: Int = 0,
//        @Embedded(prefix = "weather_")
//        var weather: List<Weather>
)



//        val base: String,
//        val clouds: Clouds,
//        val cod: Int,
//        val coord: Coord,
//        val dt: Int,
//        val id: Int,
//        val timezone: Int,
//        val wind: Wind
