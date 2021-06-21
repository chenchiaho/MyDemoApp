package com.example.android.myresumeapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.databinding.FragmentResumeBinding


class ResumeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentResumeBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.weatherNav.setOnClickListener { navToWeather() }

        return binding.root
    }

    private fun navToWeather() {
        this.findNavController().navigate(ResumeFragmentDirections.actionResumeFragmentToWeatherFragment())
    }

}