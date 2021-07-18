package com.example.android.mydemoapp.ui.weather.future

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.databinding.FutureDetailFragmentBinding
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.future_detail_fragment.*
import kotlinx.android.synthetic.main.item_future.view.*


class DetailFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FutureDetailFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.future_detail_fragment,
                container,false
        )

        binding.lifecycleOwner = viewLifecycleOwner


        val weather = DetailFragmentArgs.fromBundle(requireArguments()).selectedWeather
        binding.weatherDetail = weather

        binding.detailCity.text = weather.name
        binding.detailCondition.text = weather.description

        binding.detailTemperature.text = "${weather.temp}°C"
        binding.detailFeelsLike.text = "Feels like ${weather.feelsLike}°C"
        binding.detailMinTemp.text = "Min: ${weather.minTemp}°C"
        binding.detailMaxTemp.text = "Max: ${weather.maxTemp}°C"
        binding.detailHumidity.text = "Humidity: ${weather.humidity}%"
        binding.detailVisibility.text = "Visibility: ${weather.visibility / 1000}Km"
        val strs = weather.date.split(" ").toTypedArray()
        binding.detailDate.text = strs[0]
        binding.detailTime.text = strs[1].removeRange(4, 7)

        val imageURL = "http://openweathermap.org/img/wn/${weather.icon}@2x.png"
        imageURL.let {
            val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
            Glide.with(binding.detailIcon.context)
                    .load(imgUri)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.outline_broken_image))
                    .into(binding.detailIcon)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_detail)
    }
}