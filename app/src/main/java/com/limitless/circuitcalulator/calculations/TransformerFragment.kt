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
        if (inputBox1.text.isEmpty() || inputBox2.text.isEmpty() || inputBox3.text.isEmpty()) {
            Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
        }else {
            val a = inputBox1.text.toString().toDouble()
            val b = inputBox2.text.toString().toDouble()
            val c = inputBox3.text.toString().toDouble()
            val ab: Double = (a * b) / (a + b + c)
            val bc: Double = (b * c) / (a + b + c)
            val ac: Double = (a * c) / (a + b + c)
            resultTextView.text = buildString {
                append("The Resulting Delta Connection:\n\n")
                append("AB = (a * b) / (a + b+ c) = ")
                append("%.2f".format(ab))
                append(" Ohms\n\n")
                append("BC = (b * c) / (a + b+ c) = ")
                append("%.2f".format(bc))
                append(" Ohms\n\n")
                append("AC = (a * c) / (a + b+ c) = ")
                append("%.2f".format(ac))
                append(" Ohms\n\n")
            }
        }
    }
    private fun calculateDeltaParam() {
        if (inputBox1.text.isEmpty()|| inputBox2.text.isEmpty() || inputBox3.text.isEmpty()) {
            Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
        }else {
            val a = inputBox1.text.toString().toDouble()
            val b = inputBox2.text.toString().toDouble()
            val c = inputBox3.text.toString().toDouble()

            val A = a + b + ((a * b)/c)
            val B = b + c + ((b * c)/a)
            val C = a + c + ((a * c)/b)
            resultTextView.text = buildString {
                append("The Resulting Star Connection:\n\n")
                append("A = a + b + ((a * b)/c) = ")
                append("%.2f".format(A))
                append(" Ohms\n\n")
                append("B = b + c + ((b * c)/a) = ")
                append("%.2f".format(B))
                append(" Ohms\n\n")
                append("C = a + c + ((a * c)/b) = ")
                append("%.2f".format(C))
                append(" Ohms\n\n")
            }
        }
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