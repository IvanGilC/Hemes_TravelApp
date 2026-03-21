package com.example.hermes_travelapp.domain.model

import java.time.LocalDate
import java.time.LocalTime

/**
 * Represents a single scheduled activity within a Trip's itinerary.
 *
 * @property id Unique identifier for the activity.
 * @property tripId Identifier for the trip this activity belongs to.
 * @property dayId Identifier for the specific day within the trip.
 * @property title Name of the activity.
 * @property description Detailed explanation of the activity.
 * @property date The date the activity takes place.
 * @property time The time the activity starts.
 * @property location Physical location or venue.
 * @property cost Estimated cost in euros.
 */
data class ItineraryItem(
    val id: String,
    val tripId: String,
    val dayId: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val time: LocalTime,
    val location: String? = null,
    val cost: Double? = null
) {
    /**
     * Validates if all required fields for an activity are present and valid.
     *
     * @return True if [id], [tripId], [dayId], [title], and [description] are not blank.
     */
    fun isValid(): Boolean {
        return id.isNotBlank() &&
                tripId.isNotBlank() &&
                dayId.isNotBlank() &&
                title.isNotBlank() &&
                description.isNotBlank()
    }
}
