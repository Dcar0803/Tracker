package com.example.tracker

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterEntryDao {

    @Query("SELECT COUNT(*) FROM water_entries")
    fun getTotalEntries(): LiveData<Int>

    @Query("SELECT SUM(amount) FROM water_entries")
    fun getTotalAmount(): LiveData<Int>

    @Query("SELECT * FROM water_entries")
    fun getAllEntries(): LiveData<List<WaterEntry>>

    @Insert
    suspend fun insertEntry(entry: WaterEntry)
}

