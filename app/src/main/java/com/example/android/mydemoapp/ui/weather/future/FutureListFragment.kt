package com.example.android.mydemoapp.ui.weather.future

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.databinding.FutureListFragmentBinding
import com.example.android.mydemoapp.repository.DemoRepository
import com.example.android.mydemoapp.ui.weather.current.CurrentWeatherViewModel
import com.example.android.mydemoapp.ui.weather.current.CurrentWeatherViewModelFactory
import kotlinx.android.synthetic.main.current_weather_fragment.*


class FutureListFragment : Fragment() {

    private val viewModel: FutureListViewModel by lazy {
        val repository = DemoRepository.from(requireContext())
        ViewModelProvider(this, FutureListViewModelFactory(repository)).get(FutureListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FutureListFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.future_list_fragment,
            container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val futureAdapter = FutureListAdapter()
        binding.recyclerView.adapter = futureAdapter

        viewModel.futureWeather.observe(viewLifecycleOwner, Observer { future ->
            futureAdapter.submitList(future)
        })


        return binding.root
    }


}