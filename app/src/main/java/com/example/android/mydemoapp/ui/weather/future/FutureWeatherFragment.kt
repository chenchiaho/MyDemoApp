package com.example.android.mydemoapp.ui.weather.future

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.mydemoapp.databinding.FutureWeatherFragmentBinding
import kotlinx.android.synthetic.main.current_weather_fragment.*

import kotlinx.android.synthetic.main.future_weather_fragment.*

class FutureWeatherFragment : Fragment() {


    private lateinit var viewModel: FutureWeatherViewModel
    private lateinit var binding: FutureWeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FutureWeatherFragmentBinding.inflate(inflater)


        return binding.root
    }


}