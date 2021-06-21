package com.example.android.myresumeapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.myresumeapp.data.db.entity.Main

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert (currentWeather: Main)

    @Query("SELECT * FROM current_weather")
    fun getWeatherMetric(): LiveData<List<Main>>
}