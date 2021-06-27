package com.example.android.myresumeapp.ui.weather.future

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.myresumeapp.api.WeatherService
import com.example.android.myresumeapp.databinding.FutureWeatherFragmentBinding
import kotlinx.android.synthetic.main.current_weather_fragment.*

import kotlinx.android.synthetic.main.future_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FutureWeatherFragment : Fragment() {


    private lateinit var viewModel: FutureWeatherViewModel
    private lateinit var binding: FutureWeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FutureWeatherFragmentBinding.inflate(inflater)

        val api = WeatherService
        GlobalScope.launch(Dispatchers.Main) {
            val current = api.api.getCurrentWeatherMetric("london")
//            temp3.text = current.body().toString()
        }

        return binding.root
    }


}