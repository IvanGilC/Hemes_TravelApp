package com.example.hermes_travelapp.domain.model

import java.util.UUID

/**
 * Represents a travel trip.
 * Root entity for itinerary and budget management.
 *
 * @property id Unique identifier for the trip.
 * @property title Name of the trip.
 * @property startDate Starting date in "dd/MM/yyyy" format.
 * @property endDate Ending date in "dd/MM/yyyy" format.
 * @property description A brief overview of the trip's purpose or destination.
 * @property emoji Representative icon for the trip.
 * @property budget Allocated budget for the trip.
 * @property spent Total amount spent so far.
 * @property progress Percentage of the trip completed.
 * @property daysRemaining Calculated days until the trip begins.
 */
data class Trip(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val emoji: String = "🌍",
    val budget: Int = 0,
    val spent: Int = 0,
    val progress: Float = 0.0f,
    val daysRemaining: Int = 0
) {

    /**
     * Calculates the actual days remaining until the trip starts.
     *
     * @return Number of days remaining.
     */
    fun calculateDaysRemaining(): Int {
        // @TODO Implement date calculation logic using startDate and current date
        return daysRemaining
    }

    /**
     * Checks if the current expenses have exceeded the allocated budget.
     *
     * @return True if [spent] is greater than [budget].
     */
    fun isOverBudget(): Boolean {
        return spent > budget
    }

    /**
     * Generates a string summary of the trip for sharing or display.
     */
    fun getSummary(): String {
        return "$title: $description"
    }
}
