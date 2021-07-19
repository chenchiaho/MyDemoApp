package com.example.android.mydemoapp.ui.weather.current

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.api.current.WeatherParcel
import com.example.android.mydemoapp.databinding.CurrentWeatherFragmentBinding
import com.example.android.mydemoapp.repository.DemoRepository
import kotlinx.android.synthetic.main.current_weather_fragment.*


class CurrentWeatherFragment : Fragment() {

    private val viewModel: CurrentWeatherViewModel by lazy {
        val repository = DemoRepository.from(requireContext())
        ViewModelProvider(this, CurrentWeatherViewModelFactory(repository)).get(CurrentWeatherViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        val binding: CurrentWeatherFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.current_weather_fragment,
                container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.current = viewModel


        viewModel.weatherData.observe(viewLifecycleOwner, Observer {

            if (it.isNotEmpty()) {
                val data = it[0]

                textView_city.text = data.name
                textView_condition.text = data.description

                textView_temperature.text = "${data.temp}°C"
                textView_feels_like_temperature.text = "Feels like ${data.feelsLike}°C"
                textView_min_temp.text = "Min: ${data.minTemp}°C"
                textView_max_temp.text = "Max: ${data.maxTemp}°C"
                textView_humidity.text = "Humidity: ${data.humidity}%"
                textView_visibility.text = "Visibility: ${data.visibility / 1000}Km"

                val imageURL = "http://openweathermap.org/img/wn/${data.icon}@2x.png"
                imageURL.let {
                    val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
                    Glide.with(imageView_condition_icon.context)
                            .load(imgUri)
                            .apply(RequestOptions()
                                    .placeholder(R.drawable.loading_animation)
                                    .error(R.drawable.outline_broken_image))
                            .into(imageView_condition_icon)
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_current)
    }

}