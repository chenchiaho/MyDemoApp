package com.example.android.mydemoapp.database.current

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
    fun getCurrentWeatherTable(): LiveData<List<WeatherEntity>>
}