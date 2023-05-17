package com.example.navtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navtest.databinding.FragmentFrag2Binding

class Frag2 : Fragment() {

    private var _binding: FragmentFrag2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFrag2Binding.inflate(inflater, container, false)

        binding.textView2.setOnClickListener { navToFrag1() }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navToFrag1() {
        findNavController().navigate(Frag2Directions.navigateToFrag1())
    }
}