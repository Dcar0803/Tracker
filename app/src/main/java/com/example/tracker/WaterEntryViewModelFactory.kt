package com.example.tracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WaterEntryViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WaterEntryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WaterEntryViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
