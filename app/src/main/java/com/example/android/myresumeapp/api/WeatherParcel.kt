package com.example.android.myresumeapp.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherParcel (
        val id: Long,
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