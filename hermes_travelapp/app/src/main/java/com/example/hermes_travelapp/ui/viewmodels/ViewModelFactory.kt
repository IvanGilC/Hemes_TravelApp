package com.example.hermes_travelapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hermes_travelapp.data.PreferencesManager
import com.example.hermes_travelapp.domain.repository.TripDayRepository
import com.example.hermes_travelapp.domain.repository.TripRepository

class ViewModelFactory(
    private val tripRepository: TripRepository? = null,
    private val tripDayRepository: TripDayRepository? = null,
    private val preferencesManager: PreferencesManager? = null
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TripViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                TripViewModel(tripRepository ?: throw IllegalArgumentException("TripRepository is required for TripViewModel")) as T
            }
            modelClass.isAssignableFrom(TripDayViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                TripDayViewModel(tripDayRepository ?: throw IllegalArgumentException("TripDayRepository is required for TripDayViewModel")) as T
            }
            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                AccountViewModel(preferencesManager ?: throw IllegalArgumentException("PreferencesManager is required for AccountViewModel")) as T
            }
            modelClass.isAssignableFrom(ThemeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                ThemeViewModel(preferencesManager ?: throw IllegalArgumentException("PreferencesManager is required for ThemeViewModel")) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
