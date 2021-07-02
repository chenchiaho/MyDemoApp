package com.example.android.myresumeapp.ui.weather.current

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.api.WeatherApi
import com.example.android.myresumeapp.databinding.CurrentWeatherFragmentBinding
import com.example.android.myresumeapp.repository.DemoRepository
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.future_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrentWeatherFragment : Fragment() {

    private val viewModel: CurrentWeatherViewModel by lazy {
        val repository = DemoRepository.from(requireContext())
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

        val api = WeatherApi
        GlobalScope.launch(Dispatchers.Main) {
            val current = api.weatherData.getCurrentWeatherMetric("london")
            textView_city.text = current.name
            textView_condition.text = current.weatherList[0].description

            textView_temperature.text = "${current.main.temp}°C"

            textView_feels_like_temperature.text = "Feels like ${current.main.feelLike}°C"
            textView_min_temp.text = "Min: ${current.main.minTemp}°C"
            textView_max_temp.text = "Max: ${current.main.maxTemp}°C"
            textView_humidity.text = "Humidity: ${current.main.humidity}%"
            textView_visibility.text = "Visibility: ${current.visibility / 1000}Km"

        }


        return binding.root
    }

}