package com.example.project.vPagerFragment.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.project.R
import com.example.project.databinding.FragmentBoarding1Binding

class Boarding1 : Fragment() {
    private lateinit var binding: FragmentBoarding1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_boarding1,
            container, false
        )
        binding = FragmentBoarding1Binding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.txtHalNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }
}