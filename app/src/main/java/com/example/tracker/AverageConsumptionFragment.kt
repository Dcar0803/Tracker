package com.example.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class AverageConsumptionFragment : Fragment() {

    private lateinit var averageConsumptionTextView: TextView
    private lateinit var waterEntryViewModel: WaterEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_average_consumption, container, false)

        averageConsumptionTextView = root.findViewById(R.id.averageConsumptionTextView)

        waterEntryViewModel = ViewModelProvider(this).get(WaterEntryViewModel::class.java)

        // Observe changes in the average consumption
        waterEntryViewModel.averageConsumption.observe(viewLifecycleOwner, Observer { average ->
            averageConsumptionTextView.text = "Average Consumption: $average ml"
        })

        return root
    }
}
