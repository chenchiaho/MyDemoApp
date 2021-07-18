package com.example.android.mydemoapp.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.mydemoapp.R
import com.example.android.mydemoapp.databinding.FragmentOverviewBinding
import com.example.android.mydemoapp.repository.DemoRepository


class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        val repository = DemoRepository.from(requireContext())
        ViewModelProvider(this, OverviewViewModelFactory(repository)).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentOverviewBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_overview,
                container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        binding.repository = viewModel.repository


        viewModel.repository.editImpression.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Your input is: $it", Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.title_overview)
    }

}