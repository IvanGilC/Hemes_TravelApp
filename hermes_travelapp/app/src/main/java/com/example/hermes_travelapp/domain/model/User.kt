package com.example.hermes_travelapp.domain.model

/**
 * Represents a registered user of the Hermes Travel App.
 *
 * @property id Unique identifier for the user.
 * @property name Full name of the user.
 * @property email Registered email address for authentication.
 * @property profileInitials Short display initials (e.g., "JD").
 * @property activeTripCount Number of trips currently planned or in progress.
 * @property countriesVisited Total count of unique countries visited by the user.
 */
data class User(
    val id: String,
    val name: String,
    val email: String,
    val profileInitials: String,
    val activeTripCount: Int,
    val countriesVisited: Int
) {

    /**
     * Computes the user's initials based on their [name] if [profileInitials] is empty.
     *
     * @return The initials in uppercase.
     */
    fun computeInitials(): String {
        // @TODO Implement logic to compute initials from name (e.g., "Alex Johnson" -> "AJ")
        return profileInitials.uppercase()
    }

    /**
     * Validates if the [email] string follows a standard email pattern.
     *
     * @return True if the email format is valid.
     */
    fun isEmailValid(): Boolean {
        // @TODO Implement robust email validation using Patterns.EMAIL_ADDRESS
        return email.contains("@")
    }

    /**
     * Authenticates the user with the provided credentials.
     */
    fun login() {
        // @TODO Implement Login
    }

    /**
     * Registers a new user account in the system.
     */
    fun register() {
        // @TODO Implement Registration
    }

    /**
     * Ends the current user session and clears local authentication data.
     */
    fun signOut() {
        // @TODO Implement SignOut
    }

    /**
     * Refreshes the user's statistics and trip counters from the data source.
     */
    fun refreshData() {
        // @TODO Refresh trip counts and statistics
    }
}
