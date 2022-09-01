package com.limitless.circuitcalulator.calculations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.limitless.circuitcalulator.R
import com.limitless.circuitcalulator.databinding.FragmentResistorColorCodeBinding
import com.limitless.circuitcalulator.utilities.ColourCode
import com.limitless.circuitcalulator.viewModels.ColorCodeViewModel
import com.limitless.circuitcalulator.viewModels.ColorCodeViewModelFactory
import kotlin.math.pow


class ResistorColorCodeFragment : Fragment(), AdapterView.OnItemSelectedListener {
        companion object {
            fun newInstance() = ResistorColorCodeFragment()
        }
    private lateinit var viewModel: ColorCodeViewModel
    private lateinit var viewModelFactory: ColorCodeViewModelFactory
    private lateinit var binding: FragmentResistorColorCodeBinding
    private lateinit var colourSpinner1: Spinner
    private lateinit var colourSpinner2: Spinner
    private lateinit var colourSpinner3: Spinner
    private lateinit var colourSpinner4: Spinner
    private lateinit var calculateBtn: Button
    private lateinit var toolbar: Toolbar
    private lateinit var selectedColours: ColourCode
    private lateinit var resultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResistorColorCodeBinding.inflate(inflater, container, false)
        colourSpinner1 = binding.firstColorSpinner
        colourSpinner2 = binding.secondColorSpinner
        colourSpinner3 = binding.thirdColourSpinner
        colourSpinner4 = binding.fourthColourSpinner
        resultTextView = binding.resistorValue
        val application = requireActivity().application
        viewModelFactory = ColorCodeViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[ColorCodeViewModel::class.java]

        calculateBtn = binding.colorCalcButton
        calculateBtn.setOnClickListener { calculateResistorValue() }

        toolbar = binding.colorCodeToolbar
        toolbar.title = getString(R.string.color_code_frag_title)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.action_resistorColorCodeFragment_to_mainScreenFragment)
        }

        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.resistor_colour_array,
            android.R.layout.simple_spinner_item
            ).also { arrayAdapter ->
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                colourSpinner1.adapter = arrayAdapter
                colourSpinner2.adapter = arrayAdapter
                colourSpinner3.adapter = arrayAdapter
            }
        }
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.tolerance_colour_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                colourSpinner4.adapter = adapter
            }
        }
        return binding.root
    }

    private fun calculateResistorValue() {
        getSelectedColours()
        val firstBand = selectedColours.colour1
        val secondBand = selectedColours.colour2
        val thirdBand = selectedColours.colour3
        val fourthBand = selectedColours.colour4

        resultTextView.text = viewModel.calculateResistorValue(firstBand, secondBand, thirdBand, fourthBand)
    }

    private fun getSelectedColours() {
        val colour1 = colourSpinner1.selectedItemId.toInt()
        val colour2 = colourSpinner2.selectedItemId.toInt()
        val colour3 = colourSpinner3.selectedItemId.toInt()
        val colour4 = colourSpinner4.selectedItem.toString()

        selectedColours = ColourCode(colour1, colour2, colour3, colour4)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}