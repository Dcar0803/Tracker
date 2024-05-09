package com.example.tracker
// WaterEntryRepository.kt
import androidx.lifecycle.LiveData

class WaterEntryRepository(private val waterEntryDao: WaterEntryDao) {

    val totalEntries: LiveData<Int> = waterEntryDao.getTotalEntries()
    val totalAmount: LiveData<Int> = waterEntryDao.getTotalAmount()
    val allEntries: LiveData<List<WaterEntry>> = waterEntryDao.getAllEntries() // LiveData for all entries

    suspend fun insertEntry(entry: WaterEntry) {
        waterEntryDao.insertEntry(entry)
    }
}

