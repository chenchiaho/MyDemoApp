package com.example.android.myresumeapp.database.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert (currentWeather: WeatherResponse)

    @Query("SELECT * FROM current_weather")
    fun getWeatherMetric(): List<WeatherResponse>
}