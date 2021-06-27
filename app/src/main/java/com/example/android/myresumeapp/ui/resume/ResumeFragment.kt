package com.example.android.myresumeapp.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.databinding.FragmentResumeBinding
import com.example.android.myresumeapp.repository.DemoRepository


class ResumeFragment : Fragment() {

    private val viewModel: ResumeViewModel by lazy {
        val repository = DemoRepository.from(requireContext())
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        binding.repository = viewModel.repository


        viewModel.repository.editImpression.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Your input is: $it", Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }

}