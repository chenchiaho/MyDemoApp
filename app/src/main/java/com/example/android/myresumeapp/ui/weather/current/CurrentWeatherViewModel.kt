package com.example.android.myresumeapp.ui.weather.current

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myresumeapp.database.db.WeatherResponse
import com.example.android.myresumeapp.repository.ResumeRepository
import com.example.android.myresumeapp.util.ResponseStatus
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class CurrentWeatherViewModel(val repository: ResumeRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.updateCurrentWeather()
        }
    }



    private val _weatherData = MutableLiveData<List<WeatherResponse>>()
    val weatherData: LiveData<List<WeatherResponse>>
        get() = _weatherData

    init {
        try {
            _weatherData.value = repository.getCurrentWeatherMetric()
        } catch (e: Exception) {

        }
    }


    val currentWeather: MutableLiveData<ResponseStatus<WeatherResponse>> = MutableLiveData()

//    fun getCurrentWeather(location: String) = viewModelScope.launch {
//        currentWeather.postValue(ResponseStatus.Loading())
//        val response = repository.getCurrentWeather(location)
//        currentWeather.postValue(handleCurrentWeatherResponse(response))
//
//    }

    private fun handleCurrentWeatherResponse(response: Response<WeatherResponse>):
            ResponseStatus<WeatherResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ResponseStatus.Success(resultResponse)
            }
        }
        return ResponseStatus.Error(response.message())
    }
}