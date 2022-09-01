package com.limitless.circuitcalulator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.limitless.circuitcalulator.databinding.MainScreenFragmentBinding

class MainScreenFragment : Fragment() {
    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel
    private lateinit var viewModelFactory: MainScreenViewModelFactory
    private lateinit var binding: MainScreenFragmentBinding
    private lateinit var tankCircuit: ImageButton
    private lateinit var transformerParams: ImageButton
    private lateinit var ohmsLaw: ImageButton
    private lateinit var resistorColorCode: ImageButton
    private lateinit var resistorArrangement: ImageButton
    private lateinit var networkTheoremes: ImageButton
    private lateinit var toolbar: Toolbar

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

        tankCircuit = binding.tankCircuit
        tankCircuit.setOnClickListener { navigateToFragment(tankCircuit) }

        transformerParams = binding.transformerParamBtn
        transformerParams.setOnClickListener { navigateToFragment(transformerParams) }

        ohmsLaw = binding.ohmsLaw
        ohmsLaw.setOnClickListener { navigateToFragment(ohmsLaw) }

        resistorColorCode = binding.resistorColorCodes
        resistorColorCode.setOnClickListener { navigateToFragment(resistorColorCode) }

        resistorArrangement = binding.equivalentResCap
        resistorArrangement.setOnClickListener { navigateToFragment(resistorArrangement) }

        networkTheoremes = binding.networkTheorems
        networkTheoremes.setOnClickListener { navigateToFragment(networkTheoremes) }

        return binding.root
    }

    private fun navigateToFragment(buttonId: ImageButton) {
        when (buttonId) {
            tankCircuit -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_tankCircutFragment)
            }
            transformerParams -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_transformerFragment)
            }
            ohmsLaw -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_ohmsLawFragment)
            }
            resistorColorCode -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_resistorColorCodeFragment
                )
            }
            resistorArrangement -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_resistorArrangeFragment
                )
            }
            networkTheoremes -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_networkTheoremesFragment
                )
            }
        }
    }

}