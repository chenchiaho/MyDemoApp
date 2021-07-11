package com.example.android.mydemoapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
    val currentWeatherDao = database.currentWeatherDao()
    val futureWeatherDao = database.futureWeatherDao()

    val currentWeather: LiveData<List<WeatherParcel>> =
            Transformations.map(currentWeatherDao.getCurrentWeatherTable()) {
                it.asDomainModels()
            }

    suspend fun updateCurrentWeather() {
        withContext(Dispatchers.IO) {
            val weatherToday =
                    WeatherApi.weatherData.getCurrentWeatherMetric("london")
            currentWeatherDao.insertAll(weatherToday.asDatabaseModels())
        }
    }

    val futureWeather: LiveData<List<FutureWeatherParcel>> =
            Transformations.map(futureWeatherDao.getFutureWeatherTable()) {
                it.asFutureDomainModels()
            }


    private fun currentTime(): String {
        val now = LocalDateTime.now().toString()
        return now.replace("T", " ").removeRange(19, 23)
    }

    suspend fun updateFutureWeather() {

        futureWeatherDao.deleteOldEntries(currentTime())

        withContext(Dispatchers.IO) {
            val weatherFuture =
                WeatherApi.weatherData.getFutureWeatherMetric("london")

            weatherFuture.asDatabaseModels().forEach {
            futureWeatherDao.insertAllFuture(it)
            }
        }
    }


/*

    OverviewViewModel
*/

    private val moods: List<String> = listOf(
            "(👍≖‿‿≖)👍 👍(≖‿‿≖👍)", "（っ＾▿＾）",
            "≧◠ᴥ◠≦✊", "ᕙ(^▿^-ᕙ)", "≧◠‿◠≦✌", "(͡° ͜ʖ ͡°)", "(>‿◠)✌",
            "(っ＾▿＾)۶\uD83C\uDF78\uD83C\uDF1F\uD83C\uDF7A٩(˘◡˘ )", "✍(◔◡◔)", "(ㆆ_ㆆ)",
            "\uD83D\uDE2D", "\uD83D\uDCAA (•︡益︠•) \uD83D\uDC4A"
    )

    private val impressions: List<String> = listOf(
            "Awesome", "Nice",
            "Marginally favorable", "ok", "Could do better", "Pathetic",
            "Odd, empty", "\uD83D\uDC4D", "⭐",
            "Whatever", "Fun"
    )

    val editImpression = MutableLiveData<String>()

    private val _currentMood = MutableLiveData<String>()
    val currentMood: LiveData<String>
        get() = _currentMood

    private val _displayImpression = MutableLiveData<String>()
    val displayImpression: LiveData<String>
        get() = _displayImpression


    fun getRandomMood(): String {
        val random = (moods.indices).random()
        return moods[random]
    }

    fun getRandomImpression() {
        val random = (impressions.indices).random()
        editImpression.value = impressions[random]
    }

    init {
        if (loadSharedPrefMood() != null) {
            _currentMood.value = loadSharedPrefMood()!!
        } else {_currentMood.value = "456"}

        if (loadSharedPrefImpression() != null) {
            _displayImpression.value = loadSharedPrefImpression()!!
        } else {_displayImpression.value = "123"}
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

    fun loadSharedPrefImpression(): String? {
        val sharedPref = context.getSharedPreferences("sharedPrefImpression", Context.MODE_PRIVATE)
        val savedString = sharedPref.getString("IMPRESSION", null)

        return savedString
    }

    fun changeMood() {
        _currentMood.value = getRandomMood()

        val sharedPref = context.getSharedPreferences("sharedPrefMood", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            putString("MOOD", _currentMood.value)

        }.apply()
    }

    fun loadSharedPrefMood(): String? {
        val sharedPref = context.getSharedPreferences("sharedPrefMood", Context.MODE_PRIVATE)
        val savedString = sharedPref.getString("MOOD", null)

        return savedString
    }


    companion object {
        fun from(appContext: Context): DemoRepository {
            return DemoRepository(DemoDatabase.getInstance(appContext),
                    appContext)
        }
    }
}