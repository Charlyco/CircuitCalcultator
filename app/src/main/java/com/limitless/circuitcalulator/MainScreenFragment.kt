package com.limitless.circuitcalulator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.limitless.circuitcalulator.databinding.MainScreenFragmentBinding

class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel
    private lateinit var viewModelFactory: MainScreenViewModelFactory
    private lateinit var binding: MainScreenFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainScreenFragmentBinding.inflate(inflater, container, false)

        val application = requireActivity().application
        viewModelFactory = MainScreenViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainScreenViewModel::class.java]
        // TODO: Use the ViewModel

        return binding.root
    }

}