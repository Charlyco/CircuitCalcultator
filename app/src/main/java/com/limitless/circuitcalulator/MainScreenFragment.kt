package com.limitless.circuitcalulator

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.limitless.circuitcalulator.databinding.MainScreenFragmentBinding
import com.limitless.circuitcalulator.utilities.Utils

class MainScreenFragment : Fragment() {
    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel
    private lateinit var viewModelFactory: MainScreenViewModelFactory
    private lateinit var binding: MainScreenFragmentBinding
    private lateinit var rlcButton: ImageButton
    private lateinit var starDeltaButton: ImageButton
    private lateinit var ohmsLawButton: ImageButton
    private lateinit var colorCodeButton: ImageButton
    private lateinit var resistorArrangement: ImageButton
    private lateinit var networkTheoremes: ImageButton
    private lateinit var toolbar: Toolbar
    private lateinit var adView:AdView
    private lateinit var activity: MainActivity
    private var doubleBackPressed = false
    private val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainScreenFragmentBinding.inflate(inflater, container, false)

        val application = requireActivity().application
        viewModelFactory = MainScreenViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainScreenViewModel::class.java]
        // TODO: Use the ViewModel

        toolbar = binding.mainToolbar
        toolbar.title = getString(R.string.app_name)
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener{
            when(it.itemId) {
                R.id.feedback -> {
                    utils.sendFeedback(requireActivity())
                    true
                }
                else -> false
            }
        }
        rlcButton = binding.tankCircuit
        rlcButton.setOnClickListener { navigateToFragment(rlcButton) }

        starDeltaButton = binding.transformerParamBtn
        starDeltaButton.setOnClickListener { navigateToFragment(starDeltaButton) }

        ohmsLawButton = binding.ohmsLaw
        ohmsLawButton.setOnClickListener { navigateToFragment(ohmsLawButton) }

        colorCodeButton = binding.resistorColorCodes
        colorCodeButton.setOnClickListener { navigateToFragment(colorCodeButton) }

        //resistorArrangement = binding.equivalentResCap
        //resistorArrangement.setOnClickListener { navigateToFragment(resistorArrangement) }

        //networkTheoremes = binding.networkTheorems
        //networkTheoremes.setOnClickListener { navigateToFragment(networkTheoremes) }

        adView = binding.adViewMain
        MobileAds.initialize(requireActivity()) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object :
        OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackPressed) {
                    requireActivity().finishAffinity()
                }
                doubleBackPressed = true
                Toast.makeText(requireActivity(), "Press BACK again to exit", Toast.LENGTH_SHORT).show()
                Handler(Looper.myLooper()!!).postDelayed(Runnable {doubleBackPressed = false},
                    2000)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


    private fun navigateToFragment(buttonId: ImageButton) {
        when (buttonId) {
            rlcButton -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_tankCircutFragment)
            }
            starDeltaButton -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_transformerFragment)
            }
            ohmsLawButton -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_ohmsLawFragment)
            }
            colorCodeButton -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_resistorColorCodeFragment
                )
            }
//            resistorArrangement -> {
//                findNavController().navigate(
//                    R.id.action_mainScreenFragment_to_resistorArrangeFragment
//                )
//            }
//            networkTheoremes -> {
//                findNavController().navigate(
//                    R.id.action_mainScreenFragment_to_networkTheoremesFragment
//                )
//            }
        }
    }
}