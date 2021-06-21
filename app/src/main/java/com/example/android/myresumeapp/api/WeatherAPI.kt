package com.example.android.myresumeapp.api

import com.example.android.myresumeapp.api.Constants.Companion.API_KEY
import com.example.android.myresumeapp.network.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


//http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=7c7534c88bf9c7439612b68bebff8e51


interface WeatherAPI {

    @GET("current_metric")
    suspend fun getCurrentWeatherMetric (
            @Query("?q")
            location: String,
            @Query("&appid")
            apiKey: String = API_KEY,
            @Query("&units")
            units: String = "metric"
    ): Response<WeatherResponse>

    @GET("current_Imperial")
    suspend fun getCurrentWeatherImperial (
            @Query("?q")
            location: String,
            @Query("&appid")
            apiKey: String = API_KEY,
            @Query("&units")
            units: String = "imperial"
    ): Response<WeatherResponse>

}