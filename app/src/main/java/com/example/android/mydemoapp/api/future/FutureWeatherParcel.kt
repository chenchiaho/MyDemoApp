package com.example.android.mydemoapp.api.future

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FutureWeatherParcel (
    val id: Int,
    val name: String,
    val description: String,
    val visibility: Double,
    val temp: Double,
    val feelsLike: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Double,
    val icon: String,
    val date: String
): Parcelable