package com.example.navtest

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navtest.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
        private var _binding: FragmentSplashBinding? = null
        private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        Handler().postDelayed({
            navToHomePage() }, 3000)

        return binding.root
    }

    private fun navToHomePage() {
        findNavController().navigate(SplashFragmentDirections.toHomePage())
    }
}