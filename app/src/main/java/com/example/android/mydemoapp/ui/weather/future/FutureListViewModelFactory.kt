package com.example.android.mydemoapp.ui.weather.future

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.mydemoapp.repository.DemoRepository
import com.example.android.mydemoapp.ui.weather.current.CurrentWeatherViewModel


class FutureListViewModelFactory (
    val app: Application,
    private val repository: DemoRepository,
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FutureListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FutureListViewModel(app, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}