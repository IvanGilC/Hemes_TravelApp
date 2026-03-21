package com.example.hermes_travelapp.domain.repository

import com.example.hermes_travelapp.domain.model.TripDay

/**
 * Interface defining the contract for managing trip days.
 */
interface TripDayRepository {

    /**
     * Retrieves all days associated with a specific trip.
     *
     * @param tripId The unique identifier of the trip.
     * @return A list of [TripDay] objects, typically sorted by day number.
     */
    fun getDaysForTrip(tripId: String): List<TripDay>

    /**
     * Adds a new day to the trip's timeline.
     */
    fun addDay(day: TripDay)

    /**
     * Removes all days associated with a specific trip.
     */
    fun clearDaysForTrip(tripId: String)

    /**
     * Retrieves the last scheduled day for a trip.
     */
    fun getLastDayForTrip(tripId: String): TripDay?

    /**
     * Deletes a specific day by its ID.
     */
    fun deleteDay(dayId: String)
}
