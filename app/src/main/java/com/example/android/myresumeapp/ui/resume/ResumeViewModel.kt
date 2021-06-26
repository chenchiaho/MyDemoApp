package com.example.android.myresumeapp.ui.resume

import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myresumeapp.repository.ResumeRepository

class ResumeViewModel(val repository: ResumeRepository) : ViewModel(){



    val currentMood: LiveData<String>
        get() = repository.currentMood


    val displayImpression: LiveData<String>
        get() = repository.displayImpression



    fun onChangeMoodClick() = repository.changeMood()

    fun generateImpression() = repository.getRandomImpression()

    fun enterImpression() = repository.changeImpression()




}