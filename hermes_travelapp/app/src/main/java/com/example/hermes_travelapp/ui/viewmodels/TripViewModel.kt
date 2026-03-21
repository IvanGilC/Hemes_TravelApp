package com.example.hermes_travelapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hermes_travelapp.domain.model.Trip
import com.example.hermes_travelapp.domain.repository.TripRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import com.example.hermes_travelapp.R

class TripViewModel(private val repository: TripRepository) : ViewModel() {
    
    private companion object {
        const val TAG = "TripViewModel"
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    private val _errorMessageRes = MutableLiveData<Int?>(null)
    val errorMessageRes: LiveData<Int?> = _errorMessageRes

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    /**
     * Observable stream of all trips.
     */
    val trips: StateFlow<List<Trip>> = _trips.asStateFlow()

    init {
        loadTrips()
    }

    /**
     * Loads trips from the repository and updates the [trips] StateFlow.
     */
    fun loadTrips() {
        _trips.value = repository.getTrips()
        Log.d(TAG, "loadTrips: loaded ${_trips.value.size} trips")
    }

    /**
     * Validates trip dates and adds the trip if valid.
     */
    fun addTrip(trip: Trip): Boolean {
        if (validateTrip(trip)) {
            repository.addTrip(trip)
            _errorMessageRes.value = null
            loadTrips()
            return true
        }
        return false
    }

    /**
     * Validates trip dates and updates the trip if valid.
     */
    fun editTrip(updatedTrip: Trip): Boolean {
        if (validateTrip(updatedTrip)) {
            repository.editTrip(updatedTrip)
            _errorMessageRes.value = null
            loadTrips()
            return true
        }
        return false
    }

    fun deleteTrip(tripId: String) {
        repository.deleteTrip(tripId)
        loadTrips()
    }

    fun updateTripEndDate(tripId: String, newEndDate: String) {
        val trip = _trips.value.find { it.id == tripId } ?: return
        val updatedTrip = trip.copy(endDate = newEndDate)
        repository.editTrip(updatedTrip)
        loadTrips()
        Log.d(TAG, "updateTripEndDate: tripId=$tripId newEndDate=$newEndDate")
    }

    /**
     * Clears any active error messages.
     */
    fun clearError() {
        _errorMessageRes.value = null
    }

    /**
     * Core validation logic for trips.
     * Checks for mandatory dates and ensures start date is before end date.
     */
    private fun validateTrip(trip: Trip): Boolean {
        // Check for empty/null dates (since they are Strings in the domain model)
        if (trip.startDate.isBlank() || trip.endDate.isBlank()) {
            Log.e(TAG, "error_required_dates")
            _errorMessageRes.value = R.string.error_required_dates
            return false
        }

        try {
            val start = LocalDate.parse(trip.startDate, DATE_FORMATTER)
            val end = LocalDate.parse(trip.endDate, DATE_FORMATTER)

            if (!start.isBefore(end)) {
                Log.e(TAG, "error_invalid_range")
                _errorMessageRes.value = R.string.error_invalid_range
                return false
            }
        } catch (e: DateTimeParseException) {
            Log.e(TAG, "error_invalid_format: ${e.message}")
            _errorMessageRes.value = R.string.error_invalid_format
            return false
        }

        return true
    }
}
