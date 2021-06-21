package com.example.android.myresumeapp.data.db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(
        tableName = "current_weather"
)
data class Main(
        @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID,
        @SerializedName("feels_like")
    val feelsLike: Double,
        val humidity: Int,
        val pressure: Int,
        val temp: Double,
        @SerializedName("temp_max")
    val tempMax: Double,
        @SerializedName("temp_min")
    val tempMin: Double
)