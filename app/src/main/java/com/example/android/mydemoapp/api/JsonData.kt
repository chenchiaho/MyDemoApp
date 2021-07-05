package com.example.android.mydemoapp.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class WeatherContainer(
        val id: Int,
        @Json(name = "weather") val weatherList: List<ParseWeather>,
        @Json(name = "main") val main: Main,
        @Json(name = "name") val name: String,
        @Json(name = "visibility") val visibility: Double
)

@JsonClass(generateAdapter = true)
data class ParseWeather (
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
data class Main (
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feelLike: Double,
    @Json(name = "temp_min") val minTemp: Double,
    @Json(name = "temp_max") val maxTemp: Double,
    @Json(name = "humidity") val humidity: Double
)