package com.example.android.myresumeapp.ui.weather.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.databinding.CurrentWeatherFragmentBinding
import com.example.android.myresumeapp.repository.DemoRepository
import kotlinx.android.synthetic.main.current_weather_fragment.*


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

        val city = textView_city
        city.text =


        return binding.root
    }

}