package com.example.android.myresumeapp.api

import com.example.android.myresumeapp.database.db.WeatherEntity


fun List<WeatherParcel>.asDatabaseModels(): List<WeatherEntity> {
    return map {
        WeatherEntity(
                id = it.id,
                city = it.city,
                temperature = it.temperature,
                icon = it.icon
        )
    }
}

fun List<WeatherEntity>.asDomainModels(): List<WeatherParcel> {
    return map {
        WeatherParcel(
                id = it.id,
                city = it.city,
                temperature = it.temperature,
                icon = it.icon
        )
    }
}

@JvmName("asDomainModelsNetworkWeather")
fun List<ParseWeather>.asDomainModels(): List<WeatherParcel> {
    return map {
        it.asDomainModel()
    }
}

@JvmName("asDatabseModelsNetworkWeather")
fun List<ParseWeather>.asDatabaseModels(): List<WeatherEntity> {
    return map {
        it.asDatabaseModel()
    }
}


fun ParseWeather.asDomainModel(): WeatherParcel {
    return WeatherParcel(
            id = id,
            city = city,
            temperature = temperature,
            icon = icon
    )
}

fun ParseWeather.asDatabaseModel(): WeatherEntity {
    return WeatherEntity(
            id = id,
            city = city,
            temperature = temperature,
            icon = icon
    )
}