package com.example.android.mydemoapp.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mydemoapp.database.current.WeatherEntity
import com.example.android.mydemoapp.repository.DemoRepository
import com.example.android.mydemoapp.util.ResponseStatus
import kotlinx.coroutines.launch
import retrofit2.Response

class CurrentWeatherViewModel(val repository: DemoRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.updateCurrentWeather()
        }
    }

    val weatherData = repository.currentWeather



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