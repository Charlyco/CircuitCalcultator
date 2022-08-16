package com.limitless.circuitcalulator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
    private lateinit var tankCircuit: LinearLayout
    private lateinit var xformer_params: LinearLayout
    private lateinit var ohms_law: LinearLayout
    private lateinit var resistor_color_code: LinearLayout
    private lateinit var resistor_arrangemnet: LinearLayout
    private lateinit var network_theoremes: LinearLayout
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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

        tankCircuit = binding.rlcViewBtn
        tankCircuit.setOnClickListener { navigateToFragment(tankCircuit) }

        xformer_params = binding.xformerViewBtn
        xformer_params.setOnClickListener { navigateToFragment(xformer_params) }

        ohms_law = binding.ohmsLawViewBtn
        ohms_law.setOnClickListener { navigateToFragment(ohms_law) }

        resistor_color_code = binding.colorCodeViewBtn
        resistor_color_code.setOnClickListener { navigateToFragment(resistor_color_code) }

        resistor_arrangemnet = binding.resistorArngViewBtn
        resistor_arrangemnet.setOnClickListener { navigateToFragment(resistor_arrangemnet) }

        network_theoremes = binding.ntwkTheoremsViewBtn
        network_theoremes.setOnClickListener { navigateToFragment(network_theoremes) }

        return binding.root
    }

    private fun navigateToFragment(buttonId: LinearLayout) {
        when (buttonId) {
            tankCircuit -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_tankCircutFragment)
            }
            xformer_params -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_transformerFragment)
            }
            ohms_law -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_ohmsLawFragment)
            }
            resistor_color_code -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_resistorColorCodeFragment
                )
            }
            resistor_arrangemnet -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_resistorArrangeFragment
                )
            }
            network_theoremes -> {
                findNavController().navigate(
                    R.id.action_mainScreenFragment_to_networkTheoremesFragment
                )
            }
        }
    }

}