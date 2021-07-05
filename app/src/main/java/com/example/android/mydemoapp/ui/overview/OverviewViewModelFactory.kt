package com.example.android.mydemoapp.ui.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.mydemoapp.repository.DemoRepository

class OverviewViewModelFactory(
    private val repository: DemoRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }