package com.example.android.mydemoapp.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mydemoapp.repository.DemoRepository
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(val repository: DemoRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.updateCurrentWeather()
        }
    }
    val weatherData = repository.currentWeather
}