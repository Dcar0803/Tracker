package com.example.tracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private lateinit var totalEntriesTextView: TextView
    private lateinit var totalAmountTextView: TextView
    private lateinit var waterEntryViewModel: WaterEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        totalEntriesTextView = root.findViewById(R.id.totalEntriesTextView)
        totalAmountTextView = root.findViewById(R.id.totalAmountTextView)

        waterEntryViewModel = ViewModelProvider(this).get(WaterEntryViewModel::class.java)

        // Observe changes in the total entries count
        waterEntryViewModel.totalEntries.observe(viewLifecycleOwner, Observer { count ->
            totalEntriesTextView.text = "Total Entries: $count"
        })

        // Observe changes in the total amount consumed
        waterEntryViewModel.totalAmount.observe(viewLifecycleOwner, Observer { amount ->
            totalAmountTextView.text = "Total Amount: $amount ml"
        })

        return root
    }
}
