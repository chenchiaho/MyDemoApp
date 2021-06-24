package com.example.android.myresumeapp.api

import com.example.android.myresumeapp.database.db.WeatherResponse
import com.example.android.myresumeapp.util.Constants.Companion.API_KEY
import com.example.android.myresumeapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=7c7534c88bf9c7439612b68bebff8e51

//http://api.openweathermap.org/data/2.5/weather?q=london&appid=7c7534c88bf9c7439612b68bebff8e51&units=metric
interface WeatherAPI {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherMetric (
            @Query("q")
            location: String,
            @Query("appid")
            apiKey: String = API_KEY,
            @Query("units")
            units: String = "metric"
    ): WeatherResponse

    @GET("current_Imperial")
    suspend fun getCurrentWeatherImperial (
            @Query("q")
            location: String,
            @Query("appid")
            apiKey: String = API_KEY,
            @Query("units")
            units: String = "imperial"
    ): Response<WeatherResponse>

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(WeatherAPI::class.java)
        }
    }
}