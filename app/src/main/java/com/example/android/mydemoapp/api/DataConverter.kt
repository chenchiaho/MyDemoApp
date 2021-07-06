package com.example.android.mydemoapp.api

import com.example.android.mydemoapp.api.current.WeatherContainer
import com.example.android.mydemoapp.api.current.WeatherParcel
import com.example.android.mydemoapp.api.future.FutureWeatherContainer

import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.database.future.FutureEntity
import com.example.android.mydemoapp.database.current.WeatherEntity


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

fun List<WeatherEntity>.asDomainModels(): List<WeatherParcel> {
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

fun FutureWeatherContainer.asDatabaseModels(): FutureEntity {
        return FutureEntity(

                name = city.futureName,
                description = futureList[0].futureWeather[0].futureDescription,
                visibility = futureList[0].futureVisibility,
                temp = futureList[0].futureMain.futureTemp,
                feelsLike = futureList[0].futureMain.futureFeelLike,
                minTemp = futureList[0].futureMain.futureMin,
                maxTemp = futureList[0].futureMain.futureMax,
                humidity = futureList[0].futureMain.futureHumidity,
                icon = futureList[0].futureWeather[0].futureIcon,
                date = futureList[0].futureDate
        )
}

fun List<FutureEntity>.asFutureDomainModels(): List<FutureWeatherParcel> {
        return map {
                FutureWeatherParcel(

                        name = it.name,
                        description = it.description,
                        visibility = it.visibility,
                        temp = it.temp,
                        feelsLike = it.feelsLike,
                        minTemp = it.minTemp,
                        maxTemp = it.maxTemp,
                        humidity = it.humidity,
                        icon = it.icon,
                        date = it.date
                )
        }
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