package com.example.android.myresumeapp.repository

import com.example.android.myresumeapp.api.RetrofitInstance
import com.example.android.myresumeapp.data.db.ResumeDatabase


class ResumeRepository(
    val database: ResumeDatabase
) {
    suspend fun getCurrentWeather(location: String) =
        RetrofitInstance.api.getCurrentWeatherMetric(location)
}