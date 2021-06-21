package com.example.android.myresumeapp.ui.weather.future

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.databinding.FragmentWeatherBinding
import com.example.android.myresumeapp.databinding.FutureWeatherFragmentBinding

class FutureWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = FutureWeatherFragment()
    }

    private lateinit var viewModel: FutureWeatherViewModel
    private lateinit var binding: FutureWeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.future_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FutureWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}