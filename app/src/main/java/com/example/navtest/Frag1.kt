package com.example.navtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.navtest.databinding.FragmentFrag1Binding

class Frag1 : Fragment() {

    private var _binding: FragmentFrag1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFrag1Binding.inflate(inflater, container, false)

        binding.textView1.setOnClickListener { navToFrag2() }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun navToFrag2() {
        findNavController().navigate(Frag1Directions.navigateToFrag2())
    }
}