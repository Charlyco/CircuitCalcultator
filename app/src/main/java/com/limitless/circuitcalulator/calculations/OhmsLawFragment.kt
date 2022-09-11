package com.limitless.circuitcalulator.calculations



import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.limitless.circuitcalulator.R
import com.limitless.circuitcalulator.databinding.FragmentOhmsLawBinding
import com.limitless.circuitcalulator.utilities.Utils
import com.limitless.circuitcalulator.viewModels.OhmsLawViewModel
import com.limitless.circuitcalulator.viewModels.OhmsLawViewModelFactory


class OhmsLawFragment : Fragment(), View.OnTouchListener {
    companion object {
        fun newInstance() = OhmsLawFragment()
    }
    private lateinit var viewModel: OhmsLawViewModel
    private lateinit var viewModelFactory: OhmsLawViewModelFactory
    private lateinit var binding: FragmentOhmsLawBinding
    private lateinit var toolbar: Toolbar
    private lateinit var firstInputBox: EditText
    private lateinit var secondInputBox: EditText
    private lateinit var voltageButton1: ImageButton
    private lateinit var voltageButton2: ImageButton
    private lateinit var voltageButton3: ImageButton
    private lateinit var resistanceButton1: ImageButton
    private lateinit var resistanceButton2: ImageButton
    private lateinit var resistanceButton3: ImageButton
    private lateinit var powerButton1: ImageButton
    private lateinit var powerButton2: ImageButton
    private lateinit var powerButton3: ImageButton
    private lateinit var currentButton1: ImageButton
    private lateinit var currentButton2: ImageButton
    private lateinit var currentButton3: ImageButton
    private lateinit var calcButton: Button
    private var clickedButtonId: Int? = null
    private lateinit var resultBox: TextView
    private lateinit var adView: AdView
    private val utils:Utils = Utils()

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOhmsLawBinding.inflate(inflater, container, false)
        val application = requireActivity().application
        viewModelFactory = OhmsLawViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[OhmsLawViewModel::class.java]



        voltageButton1 = binding.voltageButton1
        voltageButton2 = binding.voltageButton2
        voltageButton3 = binding.voltageButton3
        resistanceButton1 = binding.resistanceButton1
        resistanceButton2 = binding.resistanceButton2
        resistanceButton3 = binding.resistanceButton3
        powerButton1 = binding.powerButton1
        powerButton2 = binding.powerButton2
        powerButton3 = binding.powerButton3
        currentButton1 = binding.currentButton1
        currentButton2 = binding.currentButton2
        currentButton3 = binding.currentButton3

        voltageButton1.setOnTouchListener(this)
        voltageButton2.setOnTouchListener(this)
        voltageButton3.setOnTouchListener(this)
        resistanceButton1.setOnTouchListener(this)
        resistanceButton2.setOnTouchListener(this)
        resistanceButton3.setOnTouchListener(this)
        powerButton1.setOnTouchListener(this)
        powerButton2.setOnTouchListener(this)
        powerButton3.setOnTouchListener(this)
        currentButton1.setOnTouchListener(this)
        currentButton2.setOnTouchListener(this)
        currentButton3.setOnTouchListener(this)

        firstInputBox = binding.ohmInputBox1
        secondInputBox = binding.ohmInputBox2
        resultBox = binding.ohmsLawResult
        calcButton = binding.ohmaCalcButton
        calcButton.setOnClickListener { calculateAndShowResult(clickedButtonId) }

        toolbar = binding.ohmsLaTtoolbar
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
                R.id.action_ohmsLawFragment_to_mainScreenFragment)
        }

        adView = binding.adViewOhmsLaw
        MobileAds.initialize(requireActivity()) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        return binding.root
    }

    private fun calculateAndShowResult(buttonId: Int?) {
        when (buttonId) {
            R.id.voltageButton1 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val p = firstInputBox.text.toString().toDouble()
                val r = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcVoltageWithPandR(p, r)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.voltageButton2 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val p = firstInputBox.text.toString().toDouble()
                val i = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcVoltageWithPandI(p, i)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.voltageButton3 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val i = firstInputBox.text.toString().toDouble()
                val r = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcVoltageWithIandR(i, r)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.resistanceButton1 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val v = firstInputBox.text.toString().toDouble()
                val i = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcResistanceWithVandI(v, i)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.resistanceButton2 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val v = firstInputBox.text.toString().toDouble()
                val p = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcResistanceWithVandP(v, p)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.resistanceButton3 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val i = firstInputBox.text.toString().toDouble()
                val p = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcResistanceWithIandP(i, p)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.powerButton1 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val v = firstInputBox.text.toString().toDouble()
                val r = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcPowerWithVandR(v, r)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.powerButton2 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val r = firstInputBox.text.toString().toDouble()
                val i = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcPowerWithRandI(r, i)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.powerButton3 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val i = firstInputBox.text.toString().toDouble()
                val v = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcPowerWithIandV(i, v)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.currentButton1 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val r = firstInputBox.text.toString().toDouble()
                val v = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcCurrentWithRandV(r, v)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.currentButton2 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty()) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val p = firstInputBox.text.toString().toDouble()
                val v = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcCurrentWithPandV(p, v)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }

            R.id.currentButton3 -> if (firstInputBox.text.isEmpty() || secondInputBox.text.isEmpty() ) {
                Toast.makeText(activity, "Input all necessary data", Toast.LENGTH_LONG).show()
            }else {
                val r = firstInputBox.text.toString().toDouble()
                val p = secondInputBox.text.toString().toDouble()
                resultBox.text = viewModel.calcCurrentWithRandP(r, p)
                firstInputBox.text.clear()
                secondInputBox.text.clear()
            }
        }
    }

    private fun showRandP(index: Int) {
        clickedButtonId = index
        currentButton3.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.resistance)
        secondInputBox.hint = getString(R.string.power)
        calcButton.visibility = View.VISIBLE
    }

    private fun showPandV(index: Int) {
        clickedButtonId = index
        currentButton2.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.power)
        secondInputBox.hint = getString(R.string.voltage)
        calcButton.visibility = View.VISIBLE
    }

    private fun showRandV(index: Int) {
        clickedButtonId = index
        currentButton1.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.resistance)
        secondInputBox.hint = getString(R.string.voltage)
        calcButton.visibility = View.VISIBLE
    }

    private fun showIandV(index: Int) {
        clickedButtonId = index
        powerButton3.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.current)
        secondInputBox.hint = getString(R.string.voltage)
        calcButton.visibility = View.VISIBLE
    }

    private fun showRandI(index: Int) {
        clickedButtonId = index
        powerButton2.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.resistance)
        secondInputBox.hint = getString(R.string.current)
        calcButton.visibility = View.VISIBLE
    }

    private fun showVandR(index: Int) {
        clickedButtonId = index
        powerButton1.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.voltage)
        secondInputBox.hint = getString(R.string.resistance)
        calcButton.visibility = View.VISIBLE
    }

    private fun showIandP(index: Int) {
        clickedButtonId = index
        resistanceButton3.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.current)
        secondInputBox.hint = getString(R.string.power)
        calcButton.visibility = View.VISIBLE
    }

    private fun showVandP(index: Int) {
        clickedButtonId = index
        resistanceButton2.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.voltage)
        secondInputBox.hint = getString(R.string.power)
        calcButton.visibility = View.VISIBLE
    }

    private fun showVandI(index: Int) {
        clickedButtonId = index
        resistanceButton1.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.voltage)
        secondInputBox.hint = getString(R.string.current)
        calcButton.visibility = View.VISIBLE
    }

    private fun showIandR(index: Int) {
        clickedButtonId = index
        voltageButton3.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.current)
        secondInputBox.hint = getString(R.string.resistance)
        calcButton.visibility = View.VISIBLE
    }

    private fun showPandI(index: Int) {
        clickedButtonId = index
        voltageButton2.setBackgroundResource(R.drawable.image_btn_bgd)
        firstInputBox.hint = getString(R.string.power)
        secondInputBox.hint = getString(R.string.current)
        calcButton.visibility = View.VISIBLE
    }

    private fun showPandR(index: Int) {
        voltageButton1.setBackgroundResource(R.drawable.image_btn_bgd)
        clickedButtonId = index
        firstInputBox.hint = getString(R.string.power)
        secondInputBox.hint = getString(R.string.resistance)
        calcButton.visibility = View.VISIBLE
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val colorPressed = Color.CYAN
        val colorNormal = Color.TRANSPARENT
       when(v) {
           voltageButton1 ->
           when (event?.action) {
               MotionEvent.ACTION_DOWN -> {
                   voltageButton1.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                   showPandR(R.id.voltageButton1)
               }
               MotionEvent.ACTION_UP -> {
                   view?.performClick()
                   voltageButton1.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
               }
           }
           voltageButton2 ->
               when (event?.action) {
               MotionEvent.ACTION_DOWN -> {
                   voltageButton2.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                   showPandI(R.id.voltageButton2)
               }
               MotionEvent.ACTION_UP -> {
                   view?.performClick()
                   voltageButton2.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
               }
           }
           voltageButton3 ->
           when (event?.action) {
               MotionEvent.ACTION_DOWN -> {
                   voltageButton3.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                   showIandR(R.id.voltageButton3)
               }
               MotionEvent.ACTION_UP -> {
                   view?.performClick()
                   voltageButton3.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
               }
           }
           resistanceButton1 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       resistanceButton1.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showVandI(R.id.resistanceButton1)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       resistanceButton1.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           resistanceButton2 ->
           when (event?.action) {
               MotionEvent.ACTION_DOWN -> {
                   resistanceButton2.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                   showVandP(R.id.resistanceButton2)
               }
               MotionEvent.ACTION_UP -> {
                   view?.performClick()
                   resistanceButton2.colorFilter = BlendModeColorFilterCompat
                       .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
               }
           }
           resistanceButton3 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       resistanceButton3.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showVandI(R.id.resistanceButton3)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       resistanceButton3.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           powerButton1 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       powerButton1.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showVandR(R.id.powerButton1)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       powerButton1.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           powerButton2 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       powerButton2.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showRandI(R.id.powerButton2)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       powerButton2.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           powerButton3 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       powerButton3.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showIandV(R.id.powerButton3)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       powerButton3.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           currentButton1 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       currentButton1.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showRandV(R.id.currentButton1)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       currentButton1.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           currentButton2 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       currentButton2.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showPandV(R.id.currentButton2)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       currentButton2.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
           currentButton3 ->
               when (event?.action) {
                   MotionEvent.ACTION_DOWN -> {
                       currentButton3.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorPressed, BlendModeCompat.SRC_ATOP)
                       showRandP(R.id.currentButton3)
                   }
                   MotionEvent.ACTION_UP -> {
                       view?.performClick()
                       currentButton3.colorFilter = BlendModeColorFilterCompat
                           .createBlendModeColorFilterCompat(colorNormal, BlendModeCompat.SRC_ATOP)
                   }
               }
       }
        return true
    }
}

