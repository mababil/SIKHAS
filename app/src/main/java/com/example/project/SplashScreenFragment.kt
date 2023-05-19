package com.example.project

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler().postDelayed({
            if (onBoardingFinished()){
                findNavController().navigate(
                    R.id.btn_login)
            } else{
                findNavController().navigate(
                    R.id.action_splashScreenFragment_to_viewPagerFragment)
            }
        }, 3000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
    private fun onBoardingFinished(): Boolean {
        val sharePref = requireActivity().getSharedPreferences("SplashScreenFragment", Context.MODE_PRIVATE)
        return sharePref.getBoolean("Selesai", false)
    }
}