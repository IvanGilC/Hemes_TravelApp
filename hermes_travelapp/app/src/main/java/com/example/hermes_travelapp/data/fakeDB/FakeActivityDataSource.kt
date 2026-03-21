package com.example.hermes_travelapp.data.fakeDB

import android.util.Log
import com.example.hermes_travelapp.domain.ItineraryItem
import java.time.LocalDate
import java.time.LocalTime

/**
 * Singleton data source providing in-memory storage for Itinerary Activities.
 */
object FakeActivityDataSource {
    private const val TAG = "FakeActivityDataSource"
    
    private val activities = mutableListOf<ItineraryItem>()

    /**
     * Returns activities filtered by trip and day, sorted by time.
     */
    fun getActivitiesForDay(tripId: String, dayId: String): List<ItineraryItem> {
        val filtered = activities
            .filter { it.tripId == tripId && it.dayId == dayId }
            .sortedBy { it.time }
        Log.d(TAG, "Fetched ${filtered.size} activities for trip $tripId, day $dayId.")
        return filtered
    }

    /**
     * Returns a single activity by its unique ID.
     */
    fun getActivityById(activityId: String): ItineraryItem? {
        val activity = activities.find { it.id == activityId }
        Log.d(TAG, "getActivityById: $activityId found=${activity != null}")
        return activity
    }

    /**
     * Adds a new activity to the in-memory list.
     */
    fun addActivity(activity: ItineraryItem) {
        activities.add(activity)
        Log.d(TAG, "Added activity: ${activity.title} (ID: ${activity.id})")
    }

    /**
     * Updates an existing activity by matching its ID.
     */
    fun updateActivity(updatedActivity: ItineraryItem) {
        val index = activities.indexOfFirst { it.id == updatedActivity.id }
        if (index != -1) {
            activities[index] = updatedActivity
            Log.d(TAG, "Updated activity: ${updatedActivity.title} (ID: ${updatedActivity.id})")
        } else {
            Log.w(TAG, "Update failed: Activity with ID ${updatedActivity.id} not found.")
        }
    }

    /**
     * Deletes an activity from the list by ID.
     */
    fun deleteActivity(activityId: String) {
        val removed = activities.removeIf { it.id == activityId }
        if (removed) {
            Log.d(TAG, "Deleted activity with ID: $activityId")
        } else {
            Log.w(TAG, "Delete failed: Activity with ID $activityId not found.")
        }
    }
}
