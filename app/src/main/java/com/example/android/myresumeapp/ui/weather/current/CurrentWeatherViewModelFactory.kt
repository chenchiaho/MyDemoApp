package com.example.android.myresumeapp.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myresumeapp.repository.ResumeRepository

class CurrentWeatherViewModelFactory(
    private val repository: ResumeRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CurrentWeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}