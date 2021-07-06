package com.example.android.mydemoapp.api.future

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FutureWeatherContainer(
//    val id: Int,
    @Json(name = "city") val city: City,
    @Json(name = "list") val futureList: List<FutureList>
)

@JsonClass(generateAdapter = true)
data class City (
    @Json(name = "name") val futureName: String
)

@JsonClass(generateAdapter = true)
data class FutureList (
    @Json(name = "dt_txt") val futureDate: String,
    @Json(name = "visibility") val futureVisibility: Double,
    @Json(name = "weather") val futureWeather: List<Weather>,
    @Json(name = "main") val futureMain: FutureMain
)

@JsonClass(generateAdapter = true)
data class Weather (
    @Json(name = "description") val futureDescription: String,
    @Json(name = "icon") val futureIcon: String
)

@JsonClass(generateAdapter = true)
data class FutureMain (
    @Json(name = "temp") val futureTemp: Double,
    @Json(name = "feels_like") val futureFeelLike: Double,
    @Json(name = "temp_min") val futureMin: Double,
    @Json(name = "temp_max") val futureMax: Double,
    @Json(name = "humidity") val futureHumidity: Double
)