package com.example.android.myresumeapp.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.myresumeapp.databinding.FragmentWeatherBinding
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherFragment : Fragment() {

    lateinit var binding: FragmentWeatherBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater)


        return binding.root
    }

}