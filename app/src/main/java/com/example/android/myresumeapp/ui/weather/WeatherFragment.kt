package com.example.android.myresumeapp.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.api.WeatherAPI
import com.example.android.myresumeapp.databinding.FragmentWeatherBinding
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class WeatherFragment : Fragment() {

    lateinit var binding: FragmentWeatherBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWeatherBinding.inflate(inflater)

//        val api = WeatherAPI
//        GlobalScope.launch(Dispatchers.Main) {
//            val current = api.api.getCurrentWeatherMetric("london")
//            temp2.text = current.body().toString()
//        }

        return binding.root
    }

}