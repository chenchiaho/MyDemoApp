package com.example.android.myresumeapp.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.myresumeapp.api.WeatherContainer


@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert (weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    fun getWeatherMetric(): List<WeatherEntity>
}