package com.example.android.mydemoapp.api.current

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherParcel (
        val id: Int,
        val name: String,
        val description: String,
        val visibility: Double,
        val temp: Double,
        val feelsLike: Double,
        val minTemp: Double,
        val maxTemp: Double,
        val humidity: Double,
        val icon: String
): Parcelable