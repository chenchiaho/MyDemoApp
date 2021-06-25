package com.example.android.myresumeapp.ui.resume

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.android.myresumeapp.MainActivity
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.database.db.CurrentWeatherDao
import com.example.android.myresumeapp.database.db.ResumeDatabase
import com.example.android.myresumeapp.databinding.FragmentResumeBinding
import com.example.android.myresumeapp.repository.ResumeRepository
import com.example.android.myresumeapp.ui.weather.current.CurrentWeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*


class ResumeFragment : Fragment() {

    private val viewModel: ResumeViewModel by lazy {
        val repository = ResumeRepository.from(requireContext().applicationContext)
        ViewModelProvider(this, ResumeViewModelFactory(repository)).get(ResumeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentResumeBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_resume,
                container, false
        )
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        
        return binding.root
    }

}