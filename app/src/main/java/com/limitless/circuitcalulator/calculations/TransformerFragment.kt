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
import com.limitless.circuitcalulator.databinding.FragmentTransformerBinding
import java.security.spec.ECFieldF2m

class TransformerFragment : Fragment() {
    companion object {
        fun newInstance() =
            TransformerFragment()
    }
    private lateinit var radioGroup: RadioGroup
    private lateinit var resultTextView: TextView
    private lateinit var calcButton: Button
    private lateinit var inputBox1: EditText
    private lateinit var inputBox2: EditText
    private lateinit var inputBox3: EditText
    private lateinit var binding: FragmentTransformerBinding
    private lateinit var toolbar: Toolbar
    var config: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransformerBinding.inflate(inflater, container, false)
        radioGroup = binding.xFormerRadioGroup
        resultTextView = binding.xformerResultTextView
        calcButton = binding.xformerCalcButton
        inputBox1 = binding.xformerParam1
        inputBox2 = binding.xformerParam2
        inputBox3 = binding.xformeerParam3

        toolbar = binding.xformerToolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(
                R.id.action_transformerFragment_to_mainScreenFragment)
        }
        radioGroup.setOnCheckedChangeListener { radioGroup,
                                                i -> radioGroup.checkedRadioButtonId
        when(i) {
            R.id.starToDeltaBtn -> fillBoxWithStarParam(i)

            R.id.deltaToStarBtn -> fillBoxWithDeltaParam(i)
            }
        }

        calcButton.setOnClickListener { config?.let { it1 -> calculateParam(it1) } }
        return binding.root
    }

    private fun calculateParam(config: Int) {
        when(config) {
            R.id.starToDeltaBtn -> calculateDeltaParam()

            R.id.deltaToStarBtn -> calculateStarParam()
        }
    }

    private fun calculateStarParam() {
        val a = inputBox1.text.toString().toDouble()
        val b = inputBox2.text.toString().toDouble()
        val c = inputBox3.text.toString().toDouble()
        val ab: Double = (a * b) / (a + b+ c)
        val bc: Double = (b * c) / (a + b+ c)
        val ac: Double = (a * c) / (a + b+ c)
        resultTextView.text = "The Resulting Delta Connection:\n\n" +
                "AB = (a * b) / (a + b+ c) = $ab Ohms\n\n" +
                "BC = (b * c) / (a + b+ c) = $bc Ohms\n\n" +
                "AC = (a * c) / (a + b+ c) = $ac Ohms\n"
    }

    private fun calculateDeltaParam() {
        val a = inputBox1.text.toString().toDouble()
        val b = inputBox2.text.toString().toDouble()
        val c = inputBox3.text.toString().toDouble()

        val A = a + b + ((a * b)/c)
        val B = b + c + ((b * c)/a)
        val C = a + c + ((a * c)/b)
        resultTextView.text = "The Resulting Star Connection:\n\n" +
                "A = a + b + ((a * b)/c) = $A Ohms\n\n" +
                "B = b + c + ((b * c)/a) = $B Ohms\n\n" +
                "C = a + c + ((a * c)/b) = $C Ohms\n"
    }

    private fun fillBoxWithStarParam(id: Int) {
        config = id
        inputBox1.hint = "ac" //left flank of star connection (left box)
        inputBox2.hint = "ab" //center flank of star connection(center box)
        inputBox3.hint = "bc" //right flank of star connection(right box)
        calcButton.visibility = View.VISIBLE
    }

    private fun fillBoxWithDeltaParam(id: Int) {
        config = id
        inputBox1.hint = "A" // flank A of delta connection (left box)
        inputBox2.hint = "B" // flank B of delta connection (center box)
        inputBox3.hint = "C" // flank C of delta connection (right box)
        calcButton.visibility = View.VISIBLE
    }


}