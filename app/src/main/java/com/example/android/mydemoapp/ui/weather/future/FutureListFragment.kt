package com.example.android.mydemoapp.ui.weather.future

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.api.future.FutureWeatherParcel
import com.example.android.mydemoapp.databinding.FutureListFragmentBinding
import com.example.android.mydemoapp.repository.DemoRepository
import com.example.android.mydemoapp.ui.weather.current.CurrentWeatherViewModel
import com.example.android.mydemoapp.ui.weather.current.CurrentWeatherViewModelFactory

import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.android.synthetic.main.item_future.*
import androidx.core.util.Pair


class FutureListFragment : Fragment() {

    private val viewModel: FutureListViewModel by lazy {
        val app = Application()
        val repository = DemoRepository.from(requireContext())
        ViewModelProvider(this, FutureListViewModelFactory(app, repository)).get(FutureListViewModel::class.java)
    }

    private lateinit var onClickWeather: FutureWeatherParcel


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

        val futureAdapter = FutureListAdapter(
            OnClickListener { futureWeather ->
                onClickWeather = futureWeather
                viewModel.futureOnClicked()
            }
        )

        binding.recyclerView.adapter = futureAdapter

        viewModel.eventFutureClicked.observe(viewLifecycleOwner, Observer { clicked ->
            if (clicked) {

                findNavController().navigate(
                        FutureListFragmentDirections.actionShowDetail(onClickWeather)
                )
                viewModel.futureClicked()
            }
        })

        viewModel.futureWeatherData.observe(viewLifecycleOwner, Observer { future ->
            futureAdapter.submitList(future)
        })



        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_future)
    }

}