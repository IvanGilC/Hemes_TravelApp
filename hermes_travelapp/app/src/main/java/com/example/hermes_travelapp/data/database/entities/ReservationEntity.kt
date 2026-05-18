package com.example.hermes_travelapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservations")
data class ReservationEntity(
    @PrimaryKey
    val id: String,
    val hotelId: String,
    val roomId: String,
    val hotelName: String,
    val roomType: String,
    val price: Double,
    val startDate: String,
    val endDate: String,
    val guestName: String,
    val guestEmail: String
)
