package com.example.android.myresumeapp.ui.resume

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.android.myresumeapp.R
import com.example.android.myresumeapp.databinding.FragmentResumeBinding
import kotlinx.android.synthetic.main.activity_main.*


class ResumeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentResumeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        
        return binding.root
    }

}