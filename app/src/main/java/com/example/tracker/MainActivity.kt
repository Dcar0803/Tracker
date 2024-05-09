package com.example.tracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var waterEntryViewModel: WaterEntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dashboardFragment = DashboardFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = WaterEntryAdapter(mutableListOf())
        recyclerView.adapter = adapter

        val db = WaterDatabase.getInstance(this)
        val waterEntryDao = db.waterEntryDao()

        waterEntryViewModel = ViewModelProvider(this, WaterEntryViewModelFactory(application)).get(WaterEntryViewModel::class.java)

        val amountEditText: EditText = findViewById(R.id.amountEditText)

        val addEntryButton: Button = findViewById(R.id.addEntryButton)
        addEntryButton.setOnClickListener {
            val amount = amountEditText.text.toString().toIntOrNull()
            if (amount != null && amount > 0) {
                val entry = WaterEntry(
                    date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                    amount = amount
                )
                waterEntryViewModel.insertEntry(entry)
                amountEditText.text.clear()
            } else {
                // Show error message or handle invalid input
            }
        }

        waterEntryViewModel.allEntries.observe(this, { entries ->
            adapter.entries = entries.toMutableList()
            adapter.notifyDataSetChanged()
        })

        // Set up BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit()
                    true
                }
                R.id.navigation_entry -> {
                    val averageConsumptionFragment = AverageConsumptionFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, averageConsumptionFragment).commit()
                    true
                }
                else -> false
            }
        }
    }
}
