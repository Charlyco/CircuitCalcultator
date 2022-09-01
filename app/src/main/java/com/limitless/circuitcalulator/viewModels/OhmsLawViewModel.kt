package com.limitless.circuitcalulator.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.math.pow
import kotlin.math.sqrt

class OhmsLawViewModel(private val application: Application) : ViewModel() {

    fun calcVoltageWithPandR(p: Double, r: Double): String {
        val result = buildString {
            append("Voltage = ")
            append("%.2f".format((p * r)))
            append(" volts")
        }
        return result
    }

    fun calcVoltageWithPandI(p: Double, i: Double): String {
        val result = buildString {
            append("Voltage = ")
            append("%.2f".format((p / i)))
            append(" volts")
        }
        return result
    }

    fun calcVoltageWithIandR(i: Double, r: Double): String {
        val result = buildString {
            append("Voltage = ")
            append("%.2f".format((i * r)))
            append(" volts")
        }
        return result
    }

    fun calcResistanceWithVandI(v: Double, i: Double): String {
        val result = buildString {
            append("Resistance = ")
            append("%.2f".format((v / i)))
            append(" ohms")
        }
        return result
    }

    fun calcResistanceWithVandP(v: Double, p: Double): String {
        val result = buildString {
            append("Resistance = ")
            append("%.2f".format((v.pow(2) / p)))
            append(" ohms")
        }
        return result
    }

    fun calcResistanceWithIandP(i: Double, p: Double): String {
        val result = buildString {
            append("Resistance = ")
            append("%.2f".format((p / i.pow(2))))
            append(" ohms")
        }
        return result
    }

    fun calcPowerWithVandR(v: Double, r: Double): String {
        val result = buildString {
            append("Power = ")
            append("%.2f".format((v.pow(2) / r)))
            append(" watts")
        }
        return result
    }

    fun calcPowerWithRandI(r: Double, i: Double): String {
        val result = buildString {
            append("Power = ")
            append("%.2f".format((i.pow(2) * r)))
            append(" watts")
        }
        return result
    }

    fun calcPowerWithIandV(i: Double, v: Double): String {
        val result = buildString {
            append("Power = ")
            append("%.2f".format((i * v)))
            append(" watts")
        }
        return result
    }

    fun calcCurrentWithRandV(r: Double, v: Double): String {
        val result = buildString {
            append("Current = ")
            append("%.2f".format((v / r)))
            append(" amperes ")
        }
        return result
    }

    fun calcCurrentWithPandV(p: Double, v: Double): String {
        val result = buildString {
            append("Current = ")
            append("%.2f".format((p / v)))
            append(" amperes")
        }
        return result
    }

    fun calcCurrentWithRandP(r: Double, p: Double): String {
        val result = buildString {
            append(" Current = ")
            append("%2.f".format(sqrt((p / r))))
            append(" amperes")
        }
        return result
    }

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