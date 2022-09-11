package com.limitless.circuitcalulator.calculations

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.limitless.circuitcalulator.R
import com.limitless.circuitcalulator.databinding.FragmentTransformerBinding
import com.limitless.circuitcalulator.utilities.Utils
import com.limitless.circuitcalulator.viewModels.TransformerViewModel
import com.limitless.circuitcalulator.viewModels.TransformerViewModelFactory
import java.security.spec.ECFieldF2m

class TransformerFragment : Fragment() {
    companion object {
        fun newInstance() =
            TransformerFragment()
    }
    private lateinit var viewModel: TransformerViewModel
    private lateinit var viewModelFactory: TransformerViewModelFactory
    private lateinit var radioGroup: RadioGroup
    private lateinit var resultTextView: TextView
    private lateinit var calcButton: Button
    private lateinit var inputBox1: EditText
    private lateinit var inputBox2: EditText
    private lateinit var inputBox3: EditText
    private lateinit var binding: FragmentTransformerBinding
    private lateinit var toolbar: Toolbar
    private lateinit var adView: AdView
    var config: Int? = null
    private lateinit var leftArrow: LottieAnimationView
    private lateinit var rightArrow: LottieAnimationView
    private val utils: Utils = Utils()

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
        leftArrow = binding.animationView3
        rightArrow = binding.animationView2
        toolbar = binding.xformerToolbar
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
        val application = requireActivity().application
        viewModelFactory = TransformerViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[TransformerViewModel::class.java]

        adView = binding.adViewTransformer
        MobileAds.initialize(requireActivity()) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

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
            resultTextView.text = viewModel.calcStarParam(a, b, c)
        }
    }
    private fun calculateDeltaParam() {
        if (inputBox1.text.isEmpty()|| inputBox2.text.isEmpty() || inputBox3.text.isEmpty()) {
            Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
        }else {
            val a = inputBox1.text.toString().toDouble()
            val b = inputBox2.text.toString().toDouble()
            val c = inputBox3.text.toString().toDouble()
            resultTextView.text = viewModel.calcDeltaParam(a, b, c)
        }
    }

    private fun fillBoxWithStarParam(id: Int) {
        config = id
        inputBox1.hint = "Ra" //left flank of star connection (left box)
        inputBox2.hint = "Rb" //center flank of star connection(center box)
        inputBox3.hint = "Rc" //right flank of star connection(right box)
        calcButton.visibility = View.VISIBLE
        rightArrow.visibility = View.VISIBLE
        leftArrow.visibility = View.INVISIBLE
    }

    private fun fillBoxWithDeltaParam(id: Int) {
        config = id
        inputBox1.hint = "Rac" // flank A of delta connection (left box)
        inputBox2.hint = "Rab" // flank B of delta connection (center box)
        inputBox3.hint = "Rbc" // flank C of delta connection (right box)
        calcButton.visibility = View.VISIBLE
        leftArrow.visibility = View.VISIBLE
        rightArrow.visibility = View.INVISIBLE
    }


}