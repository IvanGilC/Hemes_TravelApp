package com.example.hermes_travelapp.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.hermes_travelapp.domain.Trip
import com.example.hermes_travelapp.domain.TripRepository

class TripViewModel(private val repository: TripRepository) : ViewModel() {
    val trips: List<Trip> get() = repository.getTrips()

    fun addTrip(trip: Trip) {
        repository.addTrip(trip)
    }

    fun editTrip(updatedTrip: Trip) {
        repository.editTrip(updatedTrip)
    }

    fun deleteTrip(tripId: String) {
        repository.deleteTrip(tripId)
    }
}
