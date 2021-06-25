package com.example.android.myresumeapp.ui.resume

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.myresumeapp.repository.ResumeRepository

class ResumeViewModel(val repository: ResumeRepository) : ViewModel(){



    val currentMood: LiveData<String>
        get() = repository.currentMood

    fun onChangeMoodClick() = repository.changeMood()

//    @Bindable
    val editImpression = MutableLiveData<String>()

    private val _displayImpression = MutableLiveData<String>()
    val displayImpression: LiveData<String>
        get() = _displayImpression

    fun enterImpression() {
//        _displayImpression.value = editImpression.value
    }





}