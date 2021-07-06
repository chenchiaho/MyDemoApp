package com.example.android.mydemoapp.ui.weather.future

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.repository.DemoRepository
import kotlinx.coroutines.launch

class FutureListViewModel(val repository: DemoRepository) : ViewModel() {


    val futureWeather = MediatorLiveData<List<FutureWeatherParcel>>()

    init {
        futureWeather.addSource(repository.futureWeather) {
            futureWeather.value = it
        }

        viewModelScope.launch {
            repository.updateFutureWeather()
        }
    }
//    val futureWeatherData = repository.futureWeather
}