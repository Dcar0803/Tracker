package com.example.tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WaterEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WaterEntryRepository
    val totalEntries: LiveData<Int>
    val totalAmount: LiveData<Int>

    init {
        val dao = WaterDatabase.getInstance(application).waterEntryDao()
        repository = WaterEntryRepository(dao)
        totalEntries = repository.totalEntries
        totalAmount = repository.totalAmount
    }

    fun insertEntry(entry: WaterEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEntry(entry)
        }
    }
}
