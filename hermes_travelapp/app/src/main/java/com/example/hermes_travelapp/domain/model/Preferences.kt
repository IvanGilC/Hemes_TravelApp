package com.example.hermes_travelapp.domain.model

/**
 * Stores all user-configurable settings.
 *
 * @property language Selected display language.
 * @property currency Preferred currency for budget and costs.
 * @property dateFormat Format used for displaying dates.
 * @property theme Application visual theme (e.g., "Light", "Dark").
 * @property notifications Whether push notifications are enabled.
 * @property emailUpdates Whether the user receives marketing or update emails.
 * @property textSize Preferred font size scale.
 */
data class Preferences(
    val language: String = "English",
    val currency: String = "EUR (€)",
    val dateFormat: String = "DD/MM/YYYY",
    val theme: String = "Dark",
    val notifications: Boolean = true,
    val emailUpdates: Boolean = false,
    val textSize: String = "Medium"
) {

    /**
     * Saves current preferences state to local persistent storage.
     */
    fun save() {
        // @TODO Implement DataStore persistence
    }

    /**
     * Resets all application settings to their default values.
     */
    fun resetToDefaults() {
        // @TODO Implement reset logic and clear DataStore
    }
}
