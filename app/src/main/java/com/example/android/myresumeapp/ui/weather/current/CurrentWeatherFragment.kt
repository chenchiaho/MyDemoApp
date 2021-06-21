package com.example.android.myresumeapp.ui.weather.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.myresumeapp.databinding.CurrentWeatherFragmentBinding



class CurrentWeatherFragment : Fragment() {

    lateinit var viewModel: CurrentWeatherViewModel
    lateinit var binding: CurrentWeatherFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentWeatherFragmentBinding.inflate(inflater)



        return binding.root
    }

}