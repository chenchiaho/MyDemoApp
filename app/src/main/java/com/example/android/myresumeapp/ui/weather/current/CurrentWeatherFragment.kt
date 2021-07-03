package com.example.android.myresumeapp.ui.weather.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.api.WeatherApi
import com.example.android.myresumeapp.api.WeatherApi.weatherData
import com.example.android.myresumeapp.databinding.CurrentWeatherFragmentBinding
import com.example.android.myresumeapp.repository.DemoRepository
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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

        viewModel.weatherData.observe(viewLifecycleOwner, Observer {

                    if (it != null) {
                        val data = it
                        Log.d("Tag", "$data")}

//            textView_city.text = data
//                    textView_condition.text = data.description
//
//                    textView_temperature.text = "${data.temp}°C"
//
//                    textView_feels_like_temperature.text = "Feels like ${data.feelsLike}°C"
//                    textView_min_temp.text = "Min: ${data.minTemp}°C"
//                    textView_max_temp.text = "Max: ${data.maxTemp}°C"
//                    textView_humidity.text = "Humidity: ${data.humidity}%"
//                    textView_visibility.text = "Visibility: ${data.visibility / 1000}Km"
//
//                    val imageURL = "http://openweathermap.org/img/wn/${data.icon}.png"
//                    imageURL.let {
//                        val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
//                        Glide.with(imageView_condition_icon.context)
//                                .load(imgUri)
//                                .apply(RequestOptions()
//                                        .placeholder(R.drawable.loading_animation)
//                                        .error(R.drawable.outline_broken_image))
//                                .into(imageView_condition_icon)
//                    }

        })

//        val api = WeatherApi
//        GlobalScope.launch(Dispatchers.Main) {
//            val current = api.weatherData.getCurrentWeatherMetric("london")
//            textView_city.text = current.name
//            textView_condition.text = current.weatherList[0].description
//
//            textView_temperature.text = "${current.main.temp}°C"
//
//            textView_feels_like_temperature.text = "Feels like ${current.main.feelLike}°C"
//            textView_min_temp.text = "Min: ${current.main.minTemp}°C"
//            textView_max_temp.text = "Max: ${current.main.maxTemp}°C"
//            textView_humidity.text = "Humidity: ${current.main.humidity}%"
//            textView_visibility.text = "Visibility: ${current.visibility / 1000}Km"
//
//            val imageURL = "http://openweathermap.org/img/wn/${current.weatherList[0].icon}.png"
//            imageURL.let {
//                val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
//                Glide.with(imageView_condition_icon.context)
//                        .load(imgUri)
//                        .apply(RequestOptions()
//                                .placeholder(R.drawable.loading_animation)
//                                .error(R.drawable.outline_broken_image))
//                        .into(imageView_condition_icon)
//            }
//        }


        return binding.root
    }

}