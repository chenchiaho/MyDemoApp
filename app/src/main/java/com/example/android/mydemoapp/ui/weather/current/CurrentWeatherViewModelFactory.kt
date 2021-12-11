package com.example.android.mydemoapp.ui.weather.current

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.mydemoapp.MainActivity
import com.example.android.mydemoapp.repository.DemoRepository

class CurrentWeatherViewModelFactory(
    val app: Application,
    private val repository: DemoRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrentWeatherViewModel(app, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}