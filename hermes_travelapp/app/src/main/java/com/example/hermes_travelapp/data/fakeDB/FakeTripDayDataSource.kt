package com.example.hermes_travelapp.data.fakeDB

import android.util.Log
import com.example.hermes_travelapp.domain.model.TripDay

/**
 * Singleton data source providing in-memory storage for Trip Days.
 */
object FakeTripDayDataSource {
    private const val TAG = "FakeTripDayDataSource"
    
    private val days = mutableListOf<TripDay>()

    /**
     * Returns days for a specific trip, sorted by day number.
     */
    fun getDaysForTrip(tripId: String): List<TripDay> {
        val filtered = days.filter { it.tripId == tripId }.sortedBy { it.dayNumber }
        Log.d(TAG, "Fetched ${filtered.size} days for trip $tripId.")
        return filtered
    }

    /**
     * Adds a new day to the in-memory list.
     */
    fun addDay(day: TripDay) {
        days.add(day)
        Log.d(TAG, "Added day ${day.dayNumber} for trip ${day.tripId}.")
    }

    /**
     * Clears all days for a trip.
     */
    fun clearDaysForTrip(tripId: String) {
        val removed = days.removeIf { it.tripId == tripId }
        if (removed) {
            Log.d(TAG, "Cleared all days for trip: $tripId")
        }
    }

    fun getLastDayForTrip(tripId: String): TripDay? {
        return days.filter { it.tripId == tripId }.maxByOrNull { it.dayNumber }
    }

    fun deleteDay(dayId: String) {
        days.removeIf { it.id == dayId }
        Log.d(TAG, "Deleted day with ID: $dayId")
    }
}
