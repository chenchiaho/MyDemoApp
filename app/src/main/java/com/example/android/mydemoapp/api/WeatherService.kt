package com.example.android.mydemoapp.api

import com.example.android.mydemoapp.api.current.WeatherContainer
import com.example.android.mydemoapp.api.future.FutureWeatherContainer

import com.example.android.mydemoapp.util.Constants.Companion.API_KEY
import com.example.android.mydemoapp.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//http://openweathermap.org/img/wn/01d.png
//http://api.openweathermap.org/data/2.5/weather?q=london&appid=7c7534c88bf9c7439612b68bebff8e51&units=metric

/*
future
http://api.openweathermap.org/data/2.5/forecast?q=london&appid=7c7534c88bf9c7439612b68bebff8e51&units=metric
1 night, 5 day, 9, 13, 17, 21, 25, 29, 33, 37,
*/

interface WeatherService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherMetric (
            @Query("q")
            location: String,
            @Query("appid")
            apiKey: String = API_KEY,
            @Query("units")
            units: String = "metric"
    ): WeatherContainer

    @GET("data/2.5/forecast")
    suspend fun getFutureWeatherMetric (
            @Query("q")
            location: String,
            @Query("appid")
            apiKey: String = API_KEY,
            @Query("units")
            units: String = "metric"
    ): FutureWeatherContainer
}


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(Constants.BASE_URL)
        .build()

object WeatherApi {
    val weatherData: WeatherService = retrofit.create(WeatherService::class.java)
}