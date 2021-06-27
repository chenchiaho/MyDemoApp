package com.example.android.myresumeapp.api

import android.widget.ArrayAdapter
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.GeneratedAdapter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherContainer(
    @Json(name = "weather") val weatherList: List<Weather>,
    @Json(name = "main") val main: Main,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class Weather (
    @Json(name = "icon") val icon: String
)

@JsonClass(generateAdapter = true)
data class Main (
    @Json(name = "temp") val temp: Double
)