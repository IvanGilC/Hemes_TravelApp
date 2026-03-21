package com.example.hermes_travelapp.domain.repository

import com.example.hermes_travelapp.domain.model.ItineraryItem

/**
 * Interface defining the contract for managing itinerary activities.
 */
interface ActivityRepository {
    
    /**
     * Retrieves all activities scheduled for a specific day.
     *
     * @param tripId The ID of the trip.
     * @param dayId The ID of the specific day.
     * @return A list of [ItineraryItem] objects, typically sorted by time.
     */
    fun getActivitiesForDay(tripId: String, dayId: String): List<ItineraryItem>

    /**
     * Retrieves a single activity by its unique identifier.
     */
    fun getActivityById(activityId: String): ItineraryItem?

    /**
     * Adds a new activity to the itinerary.
     */
    fun addActivity(activity: ItineraryItem)

    /**
     * Updates an existing activity's information.
     */
    fun updateActivity(activity: ItineraryItem)

    /**
     * Removes an activity from the itinerary.
     */
    fun deleteActivity(activityId: String)
}
