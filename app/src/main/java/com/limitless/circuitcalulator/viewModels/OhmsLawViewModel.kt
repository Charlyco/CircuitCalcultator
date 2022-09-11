package com.limitless.circuitcalulator.viewModels

import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.math.pow
import kotlin.math.sqrt


class OhmsLawViewModel(private val application: Application) : ViewModel() {

    fun calcVoltageWithPandR(p: Double, r: Double): String {
        val result = buildString {
            append("Voltage = ")
            append(formatResult((p * r)))
            append(" volts")
        }
        return result
    }

    fun calcVoltageWithPandI(p: Double, i: Double): String {
        val result = buildString {
            append("Voltage = ")
            append(formatResult((p / i)))
            append(" volts")
        }
        return result
    }

    fun calcVoltageWithIandR(i: Double, r: Double): String {
        val result = buildString {
            append("Voltage = ")
            append(formatResult((i * r)))
            append(" volts")
        }
        return result
    }

    fun calcResistanceWithVandI(v: Double, i: Double): String {
        val result = buildString {
            append("Resistance = ")
            append(formatResult((v / i)))
            append(" ohms")
        }
        return result
    }

    fun calcResistanceWithVandP(v: Double, p: Double): String {
        val result = buildString {
            append("Resistance = ")
            append(formatResult((v.pow(2) / p)))
            append(" ohms")
        }
        return result
    }

    fun calcResistanceWithIandP(i: Double, p: Double): String {
        val result = buildString {
            append("Resistance = ")
            append(formatResult((p / i.pow(2))))
            append(" ohms")
        }
        return result
    }

    fun calcPowerWithVandR(v: Double, r: Double): String {
        val result = buildString {
            append("Power = ")
            append(formatResult((v.pow(2) / r)))
            append(" watts")
        }
        return result
    }

    fun calcPowerWithRandI(r: Double, i: Double): String {
        val result = buildString {
            append("Power = ")
            append(formatResult((i.pow(2) * r)))
            append(" watts")
        }
        return result
    }

    fun calcPowerWithIandV(i: Double, v: Double): String {
        val result = buildString {
            append("Power = ")
            append(formatResult((i * v)))
            append(" watts")
        }
        return result
    }

    fun calcCurrentWithRandV(r: Double, v: Double): String {
        val result = buildString {
            append("Current = ")
            append(formatResult((v / r)))
            append(" amperes ")
        }
        return result
    }

    fun calcCurrentWithPandV(p: Double, v: Double): String {
        val result = buildString {
            append("Current = ")
            append(formatResult((p/ v)))
            append(" amperes")
        }
        return result
    }

    fun calcCurrentWithRandP(r: Double, p: Double): String {
        val result = buildString {
            append(" Current = ")
            append(formatResult(sqrt((p / r))))
            append(" amperes")
        }
        return result
    }
    private fun formatResult(p: Double) = "%.2f".format(p)
}

    class OhmsLawViewModelFactory (private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OhmsLawViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OhmsLawViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}