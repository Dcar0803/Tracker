package com.example.tracker

import androidx.lifecycle.LiveData

class WaterEntryRepository(private val waterEntryDao: WaterEntryDao) {

    val totalEntries: LiveData<Int> = waterEntryDao.getTotalEntries()
    val totalAmount: LiveData<Int> = waterEntryDao.getTotalAmount()

    suspend fun insertEntry(entry: WaterEntry) {
        waterEntryDao.insertEntry(entry)
    }
}
