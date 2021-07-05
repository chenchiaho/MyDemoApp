package com.example.android.mydemoapp.database.current

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")

data class WeatherEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val description: String,
        val visibility: Double,
        val temp: Double,
        val feelsLike: Double,
        val minTemp: Double,
        val maxTemp: Double,
        val humidity: Double,
        val icon: String
)
