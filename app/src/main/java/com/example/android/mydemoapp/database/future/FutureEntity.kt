package com.example.android.mydemoapp.database.future

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "future_table", indices = [Index(value = ["date"], unique = true)])

data class FutureEntity(

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
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
