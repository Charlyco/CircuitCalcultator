package com.limitless.circuitcalulator.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TransformerViewModel(private val application: Application): ViewModel() {

    fun calcStarParam(a: Double, b: Double, c: Double): String {
        val ab: Double = (a * b) / (a + b + c)
        val bc: Double = (b * c) / (a + b + c)
        val ac: Double = (a * c) / (a + b + c)
        val result  = buildString {
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
        return result
    }

    fun calcDeltaParam(a: Double, b: Double, c: Double): String {
        val A = a + b + ((a * b)/c)
        val B = b + c + ((b * c)/a)
        val C = a + c + ((a * c)/b)
        val result = buildString {
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
        return result
    }

}

class TransformerViewModelFactory (private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransformerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransformerViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}