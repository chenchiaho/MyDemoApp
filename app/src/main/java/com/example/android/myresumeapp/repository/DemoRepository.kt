package com.example.android.myresumeapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.myresumeapp.api.WeatherApi
import com.example.android.myresumeapp.database.db.CurrentWeatherDao
import com.example.android.myresumeapp.database.db.ResumeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DemoRepository(
    val database: ResumeDatabase,
    val currentWeatherDao: CurrentWeatherDao,
    val context: Context
) {


    suspend fun updateCurrentWeather() {
        withContext(Dispatchers.IO) {
            val weatherToday =
                    WeatherApi.weatherData.getCurrentWeatherMetric(
                            "london"
                    )
            Log.d("weatherdata", "${weatherToday.name}")
            currentWeatherDao.upsert(weatherToday)
        }
    }


    fun getCurrentWeatherMetric() = database.currentWeatherDao().getWeatherMetric()

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
            return DemoRepository(ResumeDatabase.getInstance(appContext),
                    ResumeDatabase.getInstance(appContext).currentWeatherDao(),
                    appContext)
        }
    }
}