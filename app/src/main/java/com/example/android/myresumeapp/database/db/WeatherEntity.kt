package com.example.android.myresumeapp.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
class WeatherEntity (

    @PrimaryKey
    val id: Long,
    val city: String,
    val temperature: Double,
    val icon: String

)