package com.example.tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaterEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WaterEntryRepository
    val totalEntries: LiveData<Int>
    val totalAmount: LiveData<Int>
    val averageConsumption: LiveData<Int>
    val allEntries: LiveData<List<WaterEntry>> // LiveData for all entries

    init {
        val dao = WaterDatabase.getInstance(application).waterEntryDao()
        repository = WaterEntryRepository(dao)
        totalEntries = repository.totalEntries
        totalAmount = repository.totalAmount
        averageConsumption = MutableLiveData() // Initialize LiveData
        allEntries = repository.allEntries // Assign LiveData for all entries

        calculateAverageConsumption()
    }

    private fun calculateAverageConsumption() {
        viewModelScope.launch(Dispatchers.Main) {
            // Implement your logic to calculate average consumption here
            // For demonstration purposes, we're setting it to a static value
            (averageConsumption as MutableLiveData).value = 250 // Example value, replace with your calculation
        }
    }

    fun insertEntry(entry: WaterEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEntry(entry)
            calculateAverageConsumption() // Call calculateAverageConsumption after inserting entry
        }
    }
}
