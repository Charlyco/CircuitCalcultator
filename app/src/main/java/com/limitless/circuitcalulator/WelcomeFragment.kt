package com.limitless.circuitcalulator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.limitless.circuitcalulator.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var welcomeFragmentBinding:FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        welcomeFragmentBinding = FragmentWelcomeBinding.inflate(
            layoutInflater,
            container,
            false)

        delayScreen()
        return welcomeFragmentBinding.root
    }
    private fun delayScreen() {
        val SPLASH_DISPLAY_LENGTH = 3000
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            try {
                displayMainFragment()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }

    private fun displayMainFragment() {
        findNavController().navigate(
            R.id.action_welcomeFragment_to_mainScreenFragment)
    }
}