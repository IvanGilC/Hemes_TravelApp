package com.example.hermes_travelapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a user in the database.
 */
@Entity(tableName = "users")
data class UserEntity(
    /**
     * Unique identifier for the user.
     */
    @PrimaryKey
    val id: String,

    /**
     * Full name of the user.
     */
    val name: String,

    /**
     * Contact email of the user.
     */
    val email: String,

    /**
     * Email used to log in.
     */
    val login: String,

    /**
     * Unique username chosen by the user.
     */
    val username: String,

    /**
     * User's birthdate stored as epoch day (Long).
     */
    val birthdate: Long,

    /**
     * Physical address of the user.
     */
    val address: String,

    /**
     * Country of residence.
     */
    val country: String,

    /**
     * Contact phone number.
     */
    val phone: String,

    /**
     * Whether the user accepts receiving emails.
     */
    val acceptEmails: Boolean,

    /**
     * Initials for the profile picture placeholder.
     */
    val profileInitials: String,

    /**
     * Number of currently active trips.
     */
    val activeTripCount: Int,

    /**
     * Number of countries visited.
     */
    val countriesVisited: Int
)
