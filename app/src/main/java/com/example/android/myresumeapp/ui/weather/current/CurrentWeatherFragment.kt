package com.example.android.myresumeapp.ui.weather.current

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.api.WeatherAPI
import com.example.android.myresumeapp.databinding.CurrentWeatherFragmentBinding
import com.example.android.myresumeapp.databinding.FragmentResumeBinding
import com.example.android.myresumeapp.repository.ResumeRepository
import com.example.android.myresumeapp.ui.resume.ResumeViewModel
import com.example.android.myresumeapp.ui.resume.ResumeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CurrentWeatherFragment : Fragment() {

    private val viewModel: CurrentWeatherViewModel by lazy {
        val repository = ResumeRepository.from(requireContext())
        ViewModelProvider(this, CurrentWeatherViewModelFactory(repository)).get(CurrentWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: CurrentWeatherFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.current_weather_fragment,
                container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.currentWeatherViewmodel = viewModel

        val weatherResponse = viewModel.weatherData.value

        Log.d("London", "${weatherResponse?.get(0)?.name}")


        return binding.root
    }

}