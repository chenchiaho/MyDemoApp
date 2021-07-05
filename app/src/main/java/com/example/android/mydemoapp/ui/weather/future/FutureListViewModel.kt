package com.example.android.mydemoapp.ui.weather.future

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mydemoapp.repository.DemoRepository
import kotlinx.coroutines.launch

class FutureListViewModel(val repository: DemoRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.updateFutureWeather()
        }
    }

    val futureWeatherData = repository.futureWeather
}