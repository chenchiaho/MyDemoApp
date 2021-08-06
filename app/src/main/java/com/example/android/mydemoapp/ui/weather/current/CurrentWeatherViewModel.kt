package com.example.android.mydemoapp.ui.weather.current

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.android.mydemoapp.DemoApplication
import com.example.android.mydemoapp.api.current.WeatherParcel
import com.example.android.mydemoapp.repository.DemoRepository

import com.example.android.mydemoapp.util.ResponseStatus
import kotlinx.coroutines.launch
import java.io.IOException

class CurrentWeatherViewModel(
        app: Application,
        val repository: DemoRepository
        ) : AndroidViewModel(app) {

    val apiStatus: MutableLiveData<ResponseStatus<WeatherParcel>> = MutableLiveData()

    init {

            viewModelScope.launch {
                safeCall()
            }

    }

    val weatherData = repository.currentWeather

    private suspend fun safeCall() {
        apiStatus.postValue(ResponseStatus.Loading())
        try {
            if(hasInternetConnection()) {
                repository.updateCurrentWeather()
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