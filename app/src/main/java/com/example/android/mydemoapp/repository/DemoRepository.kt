package com.example.android.mydemoapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.api.*
import com.example.android.mydemoapp.api.current.WeatherParcel
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.database.DemoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime


class DemoRepository(
        val database: DemoDatabase,
        val context: Context
) {
    private val currentWeatherDao = database.currentWeatherDao()
    private val futureWeatherDao = database.futureWeatherDao()

    private val newLocation = getLocation()

    private fun getLocation(): String? {
        val sharedPref = context.getSharedPreferences("sharedPrefLocation", Context.MODE_PRIVATE)
        return sharedPref.getString("LOCATION", null)
    }

    suspend fun updateCurrentWeather() {

        if (newLocation != null) {
            withContext(Dispatchers.IO) {
                val weatherToday =
                        WeatherApi.weatherData
                                .getCurrentWeatherMetric(newLocation)
                currentWeatherDao.insertAll(weatherToday.asDatabaseModels())
            }
        }
    }

    val currentWeather: LiveData<List<WeatherParcel>> =
            Transformations.map(currentWeatherDao.getCurrentWeatherTable()) {
                it.asDomainModels()
            }


    private fun currentTime(): String {
        val now = LocalDateTime.now().toString()
        return now.replace("T", " ").removeRange(19, 23)
    }

    suspend fun updateFutureWeather() {
        if (newLocation != null) {
                futureWeatherDao.deleteOldEntries(currentTime())
            withContext(Dispatchers.IO) {
                val weatherFuture =
                        WeatherApi.weatherData.getFutureWeatherMetric(newLocation)

                weatherFuture.asDatabaseModels().forEach {
                    futureWeatherDao.insertAllFuture(it)
                }
            }
        }
    }

    val futureWeather: LiveData<List<FutureWeatherParcel>> =
            Transformations.map(futureWeatherDao.getFutureWeatherTable()) {
                it.asFutureDomainModels()
            }


/*
    OverviewViewModel
*/

    private val moods: List<String> = listOf(
            context.getString(R.string.mood1),
            context.getString(R.string.mood2),
            context.getString(R.string.mood3),
            context.getString(R.string.mood4),
            context.getString(R.string.mood5)
    )

    private val impressions: List<String> = listOf(
            context.getString(R.string.impression1),
            context.getString(R.string.impression2),
            context.getString(R.string.impression3),
            context.getString(R.string.impression4),
            context.getString(R.string.impression5),
            context.getString(R.string.impression6)
    )

    private val _currentMood = MutableLiveData<String>()
    val currentMood: LiveData<String>
        get() = _currentMood

    private val _displayImpression = MutableLiveData<String>()
    val displayImpression: LiveData<String>
        get() = _displayImpression


    private fun getRandomMood(): String {
        val random = (moods.indices).random()
        return moods[random]
    }

    val editImpression = MutableLiveData<String>()

    fun getRandomImpression() {
        val random = (impressions.indices).random()
        editImpression.value = impressions[random]
    }

    init {
        if (loadSharedPrefMood() != null) {
            _currentMood.value = loadSharedPrefMood()!!
        } else {_currentMood.value = context.getString(R.string.no_mood)}

        if (loadSharedPrefImpression() != null) {
            _displayImpression.value = loadSharedPrefImpression()!!
        } else {_displayImpression.value = context.getString(R.string.no_impression)}
    }


    fun changeImpression() {
        _displayImpression.value = editImpression.value

        val sharedPref = context.getSharedPreferences("sharedPrefImpression", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString("IMPRESSION", _displayImpression.value)

        }.apply()

        editImpression.value = ""
    }

    private fun loadSharedPrefImpression(): String? {
        val sharedPref = context.getSharedPreferences("sharedPrefImpression", Context.MODE_PRIVATE)
        return sharedPref.getString("IMPRESSION", null)
    }

    fun changeMood() {
        _currentMood.value = getRandomMood()

        val sharedPref = context.getSharedPreferences("sharedPrefMood", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString("MOOD", _currentMood.value)

        }.apply()
    }

    private fun loadSharedPrefMood(): String? {

        val sharedPref = context.getSharedPreferences("sharedPrefMood", Context.MODE_PRIVATE)
        return sharedPref.getString("MOOD", null)
    }

    companion object {
        fun from(appContext: Context): DemoRepository {
            return DemoRepository(
                    DemoDatabase.getInstance(appContext),
                    appContext
            )
        }
    }
}