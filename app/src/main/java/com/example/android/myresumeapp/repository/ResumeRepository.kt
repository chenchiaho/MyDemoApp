package com.example.android.myresumeapp.repository

import com.example.android.myresumeapp.api.RetrofitInstance
import com.example.android.myresumeapp.database.db.CurrentWeatherDao
import com.example.android.myresumeapp.database.db.Main
import com.example.android.myresumeapp.database.db.ResumeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ResumeRepository(
    val database: ResumeDatabase,
    val currentWeatherDao: CurrentWeatherDao
) {


    suspend fun updateCurrentWeather() {
        withContext(Dispatchers.IO) {
            val weatherToday =
                    RetrofitInstance.api.getCurrentWeatherMetric(
                            "london"
                    )
            currentWeatherDao.upsert(weatherToday)
        }
    }


    fun getCurrentWeatherMetric() = database.currentWeatherDao().getWeatherMetric()



}