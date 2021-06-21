package com.example.android.myresumeapp.ui.weather.current

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.myresumeapp.network.WeatherResponse
import com.example.android.myresumeapp.repository.ResumeRepository
import com.example.android.myresumeapp.util.ResponseStatus
import kotlinx.coroutines.launch
import retrofit2.Response

class CurrentWeatherViewModel(
    val repository: ResumeRepository
) : ViewModel() {

    init {
        getCurrentWeather("london")
    }


    val currentWeather: MutableLiveData<ResponseStatus<WeatherResponse>> = MutableLiveData()

    fun getCurrentWeather(location: String) = viewModelScope.launch {
        currentWeather.postValue(ResponseStatus.Loading())
        val response = repository.getCurrentWeather(location)
        currentWeather.postValue(handleCurrentWeatherResponse(response))
    }

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