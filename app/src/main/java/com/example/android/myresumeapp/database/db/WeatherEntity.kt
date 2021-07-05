package com.example.android.myresumeapp.database.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
        tableName = "weather_table")
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
