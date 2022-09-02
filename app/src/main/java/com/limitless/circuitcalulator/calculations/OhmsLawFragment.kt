package com.limitless.circuitcalulator.calculations

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.limitless.circuitcalulator.R
import com.limitless.circuitcalulator.databinding.FragmentOhmsLawBinding
import com.limitless.circuitcalulator.viewModels.OhmsLawViewModel
import com.limitless.circuitcalulator.viewModels.OhmsLawViewModelFactory
import com.ramotion.circlemenu.CircleMenuView
import kotlin.properties.Delegates


class OhmsLawFragment : Fragment() {
    companion object {
        fun newInstance() = OhmsLawFragment()
    }
    private lateinit var viewModel: OhmsLawViewModel
    private lateinit var viewModelFactory: OhmsLawViewModelFactory
    private lateinit var binding: FragmentOhmsLawBinding
    private lateinit var circleMenuView: CircleMenuView
    private lateinit var toolbar: Toolbar
    private lateinit var firstInputBox: EditText
    private lateinit var secondInputBox: EditText
    //private lateinit var resistance: EditText
    //private lateinit var power: EditText
    private lateinit var calcButton: Button
    private var circleMenuIndex: Int? = null
    private lateinit var resultBox: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOhmsLawBinding.inflate(inflater, container, false)
        val application = requireActivity().application
        viewModelFactory = OhmsLawViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[OhmsLawViewModel::class.java]
        circleMenuView = binding.ohmCircleMenu
        firstInputBox = binding.ohmInputBox1
        secondInputBox = binding.ohmInputBox2
        resultBox = binding.ohmsLawResult
        calcButton = binding.ohmaCalcButton
        calcButton.setOnClickListener { calculateAndShowResult(circleMenuIndex) }

        circleMenuView.eventListener = object : CircleMenuView.EventListener() {
            override fun onButtonClickAnimationStart(view: CircleMenuView, buttonIndex: Int) {
                super.onButtonClickAnimationEnd(view, buttonIndex)
                getSelectedButton(buttonIndex)
            }
        }
        toolbar = binding.ohmsLaTtoolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.action_ohmsLawFragment_to_mainScreenFragment)
        }
        return binding.root
    }

    private fun calculateAndShowResult(circleMenuIndex: Int?) {
        when (circleMenuIndex) {
            0 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val p = firstInputBox.text.toString().toDouble()
                val r = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcVoltageWithPandR(p, r)
            }

            1 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val p = firstInputBox.text.toString().toDouble()
                val i = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcVoltageWithPandI(p, i)
            }

            2 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val i = firstInputBox.text.toString().toDouble()
                val r = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcVoltageWithIandR(i, r)
            }

            3 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val v = firstInputBox.text.toString().toDouble()
                val i = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcResistanceWithVandI(v, i)
            }

            4 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val v = firstInputBox.text.toString().toDouble()
                val p = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcResistanceWithVandP(v, p)
            }

            5 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val i = firstInputBox.text.toString().toDouble()
                val p = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcResistanceWithIandP(i, p)
            }

            6 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val v = firstInputBox.text.toString().toDouble()
                val r = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcPowerWithVandR(v, r)
            }

            7 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val r = firstInputBox.text.toString().toDouble()
                val i = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcPowerWithRandI(r, i)
            }

            8 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val i = firstInputBox.text.toString().toDouble()
                val v = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcPowerWithIandV(i, v)
            }

            9 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val r = firstInputBox.text.toString().toDouble()
                val v = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcCurrentWithRandV(r, v)
            }

            10 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val p = firstInputBox.text.toString().toDouble()
                val v = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcCurrentWithPandV(p, v)
            }

            11 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty() ) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val r = firstInputBox.text.toString().toDouble()
                val p = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcCurrentWithRandP(r, p)
            }
        }
    }

    private fun getSelectedButton(buttonIndex: Int) {
        when(buttonIndex) {
            0 -> showPandR(buttonIndex)
            1 -> showPandI(buttonIndex)
            2 -> showIandR(buttonIndex)
            3 -> showVandI(buttonIndex)
            4 -> showVandP(buttonIndex)
            5 -> showIandP(buttonIndex)
            6 -> showVandR(buttonIndex)
            7 -> showRandI(buttonIndex)
            8 -> showIandV(buttonIndex)
            9 -> showRandV(buttonIndex)
            10 -> showPandV(buttonIndex)
            11 -> showRandP(buttonIndex)
        }
    }

    private fun showRandP(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.resistance)
        secondInputBox.hint = getString(R.string.power)
        calcButton.visibility = View.VISIBLE
    }

    private fun showPandV(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.power)
        secondInputBox.hint = getString(R.string.voltage)
        calcButton.visibility = View.VISIBLE
    }

    private fun showRandV(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.resistance)
        secondInputBox.hint = getString(R.string.voltage)
        calcButton.visibility = View.VISIBLE
    }

    private fun showIandV(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.current)
        secondInputBox.hint = getString(R.string.voltage)
        calcButton.visibility = View.VISIBLE
    }

    private fun showRandI(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.resistance)
        secondInputBox.hint = getString(R.string.current)
        calcButton.visibility = View.VISIBLE
    }

    private fun showVandR(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.voltage)
        secondInputBox.hint = getString(R.string.resistance)
        calcButton.visibility = View.VISIBLE
    }

    private fun showIandP(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.current)
        secondInputBox.hint = getString(R.string.power)
        calcButton.visibility = View.VISIBLE
    }

    private fun showVandP(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.voltage)
        secondInputBox.hint = getString(R.string.power)
        calcButton.visibility = View.VISIBLE
    }

    private fun showVandI(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.voltage)
        secondInputBox.hint = getString(R.string.current)
        calcButton.visibility = View.VISIBLE
    }

    private fun showIandR(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.current)
        secondInputBox.hint = getString(R.string.resistance)
        calcButton.visibility = View.VISIBLE
    }

    private fun showPandI(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.power)
        secondInputBox.hint = getString(R.string.current)
        calcButton.visibility = View.VISIBLE
    }

    private fun showPandR(index: Int) {
        circleMenuIndex = index
        firstInputBox.hint = getString(R.string.power)
        secondInputBox.hint = getString(R.string.resistance)
        calcButton.visibility = View.VISIBLE
    }

}