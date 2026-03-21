package com.example.hermes_travelapp.domain.repository

import com.example.hermes_travelapp.domain.model.Trip

/**
 * Interface defining the contract for managing trips.
 */
interface TripRepository {
    /**
     * Retrieves all trips associated with the current user.
     */
    fun getTrips(): List<Trip>

    /**
     * Adds a new trip to the data source.
     */
    fun addTrip(trip: Trip)

    /**
     * Updates an existing trip's details.
     */
    fun editTrip(trip: Trip)

    /**
     * Permanently removes a trip by its ID.
     */
    fun deleteTrip(tripId: String)
}
