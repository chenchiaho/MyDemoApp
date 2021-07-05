package com.example.android.mydemoapp.database.future

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "future_table")

data class FutureEntity(
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
    val icon: String,
    val date: String
)
