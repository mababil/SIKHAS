package com.example.project.vPagerFragment.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project.R
import com.example.project.databinding.FragmentBoarding3Binding

class Boarding3 : Fragment(){
    private lateinit var binding: FragmentBoarding3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_boarding3,container,false
        )
        binding = FragmentBoarding3Binding.bind(view)
        binding.txtHalNext.setOnClickListener{
            findNavController().navigate(
                R.id.loginActivity
            )
            onBoardingFinished()
        }
        return view
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity()
            .getSharedPreferences("MasukActivity", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Selesai", true)
        editor.apply()
    }
}