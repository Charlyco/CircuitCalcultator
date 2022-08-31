package com.limitless.circuitcalulator.calculations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.limitless.circuitcalulator.R
import com.limitless.circuitcalulator.databinding.FragmentTankCircutBinding
import kotlin.math.PI
import kotlin.math.sqrt

class TankCircuitFragment : Fragment() {
    companion object {
        fun newInstance() =
            TankCircuitFragment()
    }
    private lateinit var rlcRadioGroup: RadioGroup
    private lateinit var frequencyBox: EditText
    private lateinit var capacitanceBox: EditText
    private lateinit var inductanceBox: EditText
    private lateinit var result: TextView
    private lateinit var calButton: Button
    private lateinit var binding: FragmentTankCircutBinding
    private lateinit var toolbar:Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTankCircutBinding.inflate(inflater, container, false)
        rlcRadioGroup = binding.rlcRadioGroup
        frequencyBox = binding.freqEditText
        capacitanceBox = binding.capEditText
        inductanceBox = binding.indEditText
        calButton = binding.rlcCalcButton
        result = binding.rlcResultTextView

        rlcRadioGroup.setOnCheckedChangeListener { radioGroup,
                                                   i -> radioGroup.checkedRadioButtonId
            when (i) {
                R.id.frequencyBtn -> hideFrequency()

                R.id.capacitanceBtn -> hideCapacitance()

                R.id.inductnaceBtn -> hideInductance()
            }
        }
        calButton.setOnClickListener { calculateParam() }

        toolbar = binding.tankCctToolbar
        toolbar.title = getString(R.string.tank_circuit)
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.action_tankCircuitFragment_to_mainScreenFragment)}

        return binding.root
    }

    private fun calculateParam() {
        when (rlcRadioGroup.checkedRadioButtonId) {
            R.id.frequencyBtn -> calcFrequency()

            R.id.capacitanceBtn -> calcCapacitance()

            R.id.inductnaceBtn -> calcInductance()
        }
    }

    private fun calcInductance() {
        if (frequencyBox.text.isEmpty() || capacitanceBox.text.isEmpty()) {
            Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
        }else {
            val frequency = frequencyBox.text.toString().toDouble()
            val capacitance = capacitanceBox.text.toString().toDouble()
            val inductance = (sqrt((1 / (2 * PI * frequency))) / capacitance)

            result.text = buildString {
                append("Inductance = SquareRoot((1/2 * PI * ")
                append(frequency)
                append(") / ")
                append(capacitance)
                append(") \n\n")
                append("Capacitance = ${"%.4f".format(inductance)}H")
            }
        }
    }

    private fun calcCapacitance() {
        if (frequencyBox.text.isEmpty() || inductanceBox.text.isEmpty()) {
            Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
        }else {
            val inductance = inductanceBox.text.toString().toDouble()
            val frequency = frequencyBox.text.toString().toDouble()
            val capacitance = (sqrt((1 / (2 * PI * frequency))) / inductance)

            result.text = buildString {
                append("Capacitance = SquareRoot((1/2 * PI * ")
                append(frequency)
                append(") / ")
                append(inductance)
                append(") \n\n")
                append ("Capacitance = ${ "%.2f".format(capacitance * 1000000) }uF")
            }
        }
    }

    private fun calcFrequency() {
        if (capacitanceBox.text.isEmpty() || inductanceBox.text.isEmpty()) {
            Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
        } else {
            val capacitance = capacitanceBox.text.toString().toDouble()
            val inductance = inductanceBox.text.toString().toDouble()
            val resonanceFreq = 1 / (2 * PI * (sqrt(capacitance * inductance)))

            result.text = buildString {
                append("Resonance Frequency = 1/ (2 * PI * SquareRoot(")
                append(inductance)
                append(" * ")
                append(capacitance)
                append("). \n\n")
                append("Resonance Frequency = ${"%.1f".format(resonanceFreq)}Hz")
            }
        }
    }

    private fun hideInductance() {
        frequencyBox.visibility = View.VISIBLE
        capacitanceBox.visibility = View.VISIBLE
        inductanceBox.visibility = View.GONE
        calButton.visibility = View.VISIBLE
    }

    private fun hideCapacitance() {
        frequencyBox.visibility = View.VISIBLE
        capacitanceBox.visibility = View.GONE
        inductanceBox.visibility = View.VISIBLE
        calButton.visibility = View.VISIBLE
    }

    private fun hideFrequency() {
        frequencyBox.visibility = View.GONE
        capacitanceBox.visibility = View.VISIBLE
        inductanceBox.visibility = View.VISIBLE
        calButton.visibility = View.VISIBLE
    }


}