package com.example.android.myresumeapp.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class WeatherParcel (
        val id: Long,
        val city: String,
        val temperature: Double,
        val icon: String
): Parcelable