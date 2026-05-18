package com.example.hermes_travelapp.data.database.mapper

import com.example.hermes_travelapp.data.database.entities.ReservationEntity
import com.example.hermes_travelapp.domain.model.HotelReservation

fun HotelReservation.toEntity(): ReservationEntity {
    return ReservationEntity(
        id = id,
        hotelId = hotelId,
        roomId = roomId,
        hotelName = hotel?.name ?: "Hotel desconocido",
        roomType = room?.roomType ?: "Habitación desconocida",
        price = room?.price ?: 0.0,
        startDate = startDate,
        endDate = endDate,
        guestName = guestName,
        guestEmail = guestEmail
    )
}
