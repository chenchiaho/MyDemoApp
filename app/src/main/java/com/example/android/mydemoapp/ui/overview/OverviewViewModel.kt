package com.example.android.mydemoapp.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android.mydemoapp.repository.DemoRepository

class OverviewViewModel(val repository: DemoRepository) : ViewModel(){

    val currentMood: LiveData<String>
        get() = repository.currentMood

    val displayImpression: LiveData<String>
        get() = repository.displayImpression

    fun onChangeMoodClick() = repository.changeMood()

    fun generateImpression() = repository.getRandomImpression()

    fun enterImpression() = repository.changeImpression()




}