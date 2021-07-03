package com.example.android.myresumeapp.ui.weather.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myresumeapp.database.db.WeatherEntity
import com.example.android.myresumeapp.repository.DemoRepository
import com.example.android.myresumeapp.util.ResponseStatus
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class CurrentWeatherViewModel(val repository: DemoRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.updateCurrentWeather()
        }
    }

    val weatherData = repository.currentWeather


//    private val _weatherData = MutableLiveData<List<WeatherEntity>>()
//    val weatherData: LiveData<List<WeatherEntity>>
//        get() = _weatherData
//
//    init {
//        try {
//            _weatherData.value = repository.getCurrentWeatherMetric()
//        } catch (e: Exception) {
//
//        }
//    }


    val currentWeather: MutableLiveData<ResponseStatus<WeatherEntity>> = MutableLiveData()

//    fun getCurrentWeather(location: String) = viewModelScope.launch {
//        currentWeather.postValue(ResponseStatus.Loading())
//        val response = repository.getCurrentWeather(location)
//        currentWeather.postValue(handleCurrentWeatherResponse(response))
//
//    }

    private fun handleCurrentWeatherResponse(response: Response<WeatherEntity>):
            ResponseStatus<WeatherEntity> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return ResponseStatus.Success(resultResponse)
            }
        }
        return ResponseStatus.Error(response.message())
    }
}