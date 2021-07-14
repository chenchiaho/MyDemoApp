package com.example.android.mydemoapp.ui.weather.future

import android.location.Address
import android.location.Location
import androidx.lifecycle.*
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.repository.DemoRepository
import com.example.android.mydemoapp.util.CurrentLocation
import kotlinx.coroutines.launch

class FutureListViewModel(val repository: DemoRepository) : ViewModel() {

    val futureWeatherData = repository.futureWeather
    val location = CurrentLocation()

    init {
        viewModelScope.launch {
            repository.updateFutureWeather()
            location.updateLocation()
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

