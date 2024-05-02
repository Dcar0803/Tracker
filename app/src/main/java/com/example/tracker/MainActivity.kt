package com.example.tracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WaterEntryAdapter
    private lateinit var waterEntryDao: WaterEntryDao
    private lateinit var amountEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dashboardFragment = DashboardFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, dashboardFragment).commit()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WaterEntryAdapter(emptyList())
        recyclerView.adapter = adapter

        val db = WaterDatabase.getInstance(this)
        waterEntryDao = db.waterEntryDao()

        amountEditText = findViewById(R.id.amountEditText)

        val addEntryButton: Button = findViewById(R.id.addEntryButton)
        addEntryButton.setOnClickListener {
            val amount = amountEditText.text.toString().toIntOrNull()
            if (amount != null && amount > 0) {
                val entry = WaterEntry(
                    date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
                    amount = amount
                )
                insertEntry(entry)
                amountEditText.text.clear()
            } else {
                // Show error message or handle invalid input
            }
        }

        updateEntries()
    }

    private fun insertEntry(entry: WaterEntry) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                waterEntryDao.insertEntry(entry)
            }
            updateEntries()
        }
    }

    private fun updateEntries() {
        lifecycleScope.launch {
            val entries = withContext(Dispatchers.IO) {
                waterEntryDao.getAllEntries()
            }
            adapter = WaterEntryAdapter(entries)
            recyclerView.adapter = adapter
        }
    }
}
