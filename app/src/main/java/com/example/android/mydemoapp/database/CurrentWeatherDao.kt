package com.example.android.mydemoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (vararg weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    fun getWeatherMetric(): LiveData<List<WeatherEntity>>
}