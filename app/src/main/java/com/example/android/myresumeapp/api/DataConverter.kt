package com.example.android.myresumeapp.api

import androidx.lifecycle.Transformations.map
import androidx.room.PrimaryKey
import com.example.android.myresumeapp.database.db.WeatherEntity

fun List<WeatherEntity>.asDomainModel(): List<WeatherParcel> {
    return map {
        WeatherParcel(
                id = it.id,
                name = it.name,
                description = it.description,
                visibility = it.visibility,
                temp = it.temp,
                feelsLike = it.feelsLike,
                minTemp = it.minTemp,
                maxTemp = it.maxTemp,
                humidity = it.humidity,
                icon = it.icon
        )
    }
}
fun WeatherContainer.asDatabaseModels(): WeatherEntity {
    return  WeatherEntity(
            id = id,
            name = name,
            description = weatherList[0].description,
            visibility = visibility,
            temp = main.temp,
            feelsLike = main.feelLike,
            minTemp = main.minTemp,
            maxTemp = main.maxTemp,
            humidity = main.humidity,
            icon = weatherList[0].icon)

}

fun WeatherContainer.asDomainModel(): WeatherParcel {
    return WeatherParcel(
            id = id,
            name = name,
            description = weatherList[0].description,
            visibility = visibility,
            temp = main.temp,
            feelsLike = main.feelLike,
            minTemp = main.minTemp,
            maxTemp = main.maxTemp,
            humidity = main.humidity,
            icon = weatherList[0].icon
    )
}
//fun WeatherContainer.all(): List<WeatherParcel> {
//    return WeatherContainer.values.flatMap { it.asDomainModels() }
//}

//@JvmName("asDomainModelsNetworkWeather")
//fun List<WeatherContainer>.asDomainModels(): List<WeatherParcel> {
//    return map {
//        it.asDomainModel()
//    }
//}