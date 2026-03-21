package com.example.hermes_travelapp.domain.model

/**
 * Represents a place saved as a favorite by a user.
 *
 * @property id Unique identifier for the favorite place.
 * @property name Name of the place.
 * @property location Physical address or geographical description.
 */
data class FavoritePlace(
    val id: Int,
    val name: String,
    val location: String
) {

    /**
     * Persists this place as a favorite in the user's account.
     *
     * @param userId The ID of the user saving the place.
     */
    fun save(userId: String) {
        // @TODO Implement save logic via FavoritesRepository
    }

    /**
     * Removes this place from the user's favorites list.
     *
     * @param userId The ID of the user removing the place.
     */
    fun delete(userId: String) {
        // @TODO Implement deletion via FavoritesRepository
    }

    /**
     * Converts this favorite place into an itinerary item for a specific trip.
     *
     * @param tripId The ID of the destination trip.
     */
    fun addToTrip(tripId: String) {
        // @TODO Build ItineraryItem from this data and call TripRepository
    }
}
