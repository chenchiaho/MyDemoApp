package com.example.android.myresumeapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.myresumeapp.api.RetrofitInstance
import com.example.android.myresumeapp.database.db.CurrentWeatherDao
import com.example.android.myresumeapp.database.db.Main
import com.example.android.myresumeapp.database.db.ResumeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Math.random
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt


class ResumeRepository(
    val database: ResumeDatabase,
    val currentWeatherDao: CurrentWeatherDao
) {


    suspend fun updateCurrentWeather() {
        withContext(Dispatchers.IO) {
            val weatherToday =
                    RetrofitInstance.api.getCurrentWeatherMetric(
                            "london"
                    )
            currentWeatherDao.upsert(weatherToday)
        }
    }


    fun getCurrentWeatherMetric() = database.currentWeatherDao().getWeatherMetric()


//    For OverviewViewModel

    private val moods: List<String> = listOf(
            "(👍≖‿‿≖)👍 👍(≖‿‿≖👍)", "（っ＾▿＾）",
            "≧◠ᴥ◠≦✊", "ᕙ(^▿^-ᕙ)", "≧◠‿◠≦✌", "(͡° ͜ʖ ͡°)", "(>‿◠)✌",
            "(っ＾▿＾)۶\uD83C\uDF78\uD83C\uDF1F\uD83C\uDF7A٩(˘◡˘ )", "✍(◔◡◔)", "(ㆆ_ㆆ)",
            "\uD83D\uDE2D", "\uD83D\uDCAA (•︡益︠•) \uD83D\uDC4A"
    )

    private val _currentMood = MutableLiveData<String>()
    val currentMood: LiveData<String>
        get() = _currentMood

    fun getRandomMood(): String {

        val random = (moods.indices).random()
        return moods[random]
    }

    init {
        _currentMood.value = getRandomMood()
    }

    fun changeMood() {
        _currentMood.value = getRandomMood()
    }

    companion object {
        fun from(appContext: Context): ResumeRepository {
            return ResumeRepository(ResumeDatabase.getInstance(appContext),
                    ResumeDatabase.getInstance(appContext).currentWeatherDao())
        }
    }
}