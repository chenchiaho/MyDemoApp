package com.example.android.mydemoapp.ui.weather.future

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.databinding.FutureDetailFragmentBinding
import com.example.android.mydemoapp.databinding.FutureListFragmentBinding

class FutureDetailFragment : Fragment() {


    private lateinit var viewModel: FutureDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FutureDetailFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.future_detail_fragment,
            container, false)

        binding.lifecycleOwner = this

        val weather = FutureDetailFragmentArgs.fromBundle(requireArguments()).selectedWeather
        binding.weatherDetail = weather

        return binding.root
    }

}