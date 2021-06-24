package com.example.android.myresumeapp.ui.weather.current

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.api.WeatherAPI
import com.example.android.myresumeapp.databinding.CurrentWeatherFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CurrentWeatherFragment : Fragment() {

    lateinit var viewModel: CurrentWeatherViewModel
    lateinit var binding: CurrentWeatherFragmentBinding
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrentWeatherFragmentBinding.inflate(inflater)
//        binding.bottomNav2

//        val api = WeatherAPI
//        GlobalScope.launch(Dispatchers.Main) {
//            val current = api.api.getCurrentWeatherMetric("london")
//            temp.text = current.body().toString()
//        }


        return binding.root
    }

}