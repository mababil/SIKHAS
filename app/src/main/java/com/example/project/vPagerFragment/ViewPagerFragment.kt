package com.example.project.vPagerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.vPagerFragment.screens.Boarding1
import com.example.project.vPagerFragment.screens.Boarding2
import com.example.project.vPagerFragment.screens.Boarding3
import com.example.project.R
import com.example.project.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    lateinit var binding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_view_pager,
            container,false)
        binding = FragmentViewPagerBinding.bind(view)
        val fragmentlist = arrayListOf(
            Boarding1(),
            Boarding2(),
            Boarding3()
        )

        val adapter = ViewPagerAdapter(fragmentlist,requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        return view
    }
}