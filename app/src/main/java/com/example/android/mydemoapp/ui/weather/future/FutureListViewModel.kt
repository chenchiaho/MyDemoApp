package com.example.android.mydemoapp.ui.weather.future

import android.app.Application
import android.content.Context
import android.location.Address
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.example.android.mydemoapp.DemoApplication
import com.example.android.mydemoapp.api.current.WeatherParcel
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.repository.DemoRepository
import com.example.android.mydemoapp.util.ResponseStatus

import kotlinx.coroutines.launch
import java.io.IOException

class FutureListViewModel(
        app: Application,
        val repository: DemoRepository
        ) : AndroidViewModel(app) {

    val apiStatus: MutableLiveData<ResponseStatus<WeatherParcel>> = MutableLiveData()
    val futureWeatherData = repository.futureWeather


    init {
        viewModelScope.launch {
            safeCall()

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

    private suspend fun safeCall() {
        apiStatus.postValue(ResponseStatus.Loading())
        try {
            if(hasInternetConnection()) {
                repository.updateFutureWeather()
                apiStatus.postValue(ResponseStatus.Success("Data connected"))
            } else {
                apiStatus.postValue(ResponseStatus.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> apiStatus.postValue(ResponseStatus.Error("Network Failure"))
                else -> apiStatus.postValue(ResponseStatus.Error("Conversion Error"))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<DemoApplication>().getSystemService(
                Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}

