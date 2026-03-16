package com.example.hermes_travelapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hermes_travelapp.domain.TripRepository

class ViewModelFactory(private val repository: TripRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TripViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TripViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
