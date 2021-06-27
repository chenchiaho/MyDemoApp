package com.example.android.myresumeapp.ui.resume

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.myresumeapp.repository.DemoRepository

class ResumeViewModelFactory(
    private val repository: DemoRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ResumeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ResumeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }