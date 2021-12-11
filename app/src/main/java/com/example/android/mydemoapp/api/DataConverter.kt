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

fun FutureWeatherContainer.asDatabaseModels(): List<FutureEntity> {
    val list = mutableListOf<FutureEntity>()
    for (i in futureList.indices) {
        val entity = FutureEntity(
                name = city.futureName,
                description = futureList[i].futureWeather[0].futureDescription,
                visibility = futureList[i].futureVisibility,
                temp = futureList[i].futureMain.futureTemp,
                feelsLike = futureList[i].futureMain.futureFeelLike,
                minTemp = futureList[i].futureMain.futureMin,
                maxTemp = futureList[i].futureMain.futureMax,
                humidity = futureList[i].futureMain.futureHumidity,
                icon = futureList[i].futureWeather[0].futureIcon,
                date = futureList[i].futureDate
        )
        list.add(entity)
    }
    return list
}

fun List<FutureEntity>.asFutureDomainModels(): List<FutureWeatherParcel> {

        return map {
                FutureWeatherParcel(
                        id = it.id,
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

