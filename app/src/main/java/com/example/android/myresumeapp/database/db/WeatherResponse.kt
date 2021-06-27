package com.example.android.myresumeapp.database.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.myresumeapp.database.db.Main
import com.google.gson.annotations.SerializedName


//@Entity(
//        tableName = "current_weather"
//)
//data class WeatherResponse(
//        @PrimaryKey(autoGenerate = true)
//        var id: Int,
//        @Embedded(prefix = "main_")
//        var main: Main,
//        var name: String,
//        @Embedded(prefix = "rain_")
//        var rain: Rain,
//        @Embedded(prefix = "sys_")
//        var sys: Sys,
//        var visibility: Int,
//        @Embedded(prefix = "weather_")
//        var weather: List<Weather>
//)



//        val base: String,
//        val clouds: Clouds,
//        val cod: Int,
//        val coord: Coord,
//        val dt: Int,
//        val id: Int,
//        val timezone: Int,
//        val wind: Wind
