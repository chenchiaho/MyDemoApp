package com.example.android.mydemoapp.ui.weather.future

import androidx.lifecycle.*
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.repository.DemoRepository
import kotlinx.coroutines.launch

class FutureListViewModel(val repository: DemoRepository) : ViewModel() {


    val futureWeatherData = repository.futureWeather

    init {
        viewModelScope.launch {
            repository.updateFutureWeather()
        }
    }

    private val _eventFutureClicked = MutableLiveData(false)
    val eventFutureClicked: LiveData<Boolean>
        get() = _eventFutureClicked

    fun futureOnClicked() {
        _eventFutureClicked.value = true
    }

    fun futureClicked() {
        _eventFutureClicked.value = false
    }

}

