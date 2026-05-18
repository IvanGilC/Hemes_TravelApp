package com.example.hermes_travelapp.data.remote.mapper

import com.example.hermes_travelapp.data.remote.dto.HotelDto
import com.example.hermes_travelapp.data.remote.dto.ReservationDto
import com.example.hermes_travelapp.data.remote.dto.RoomDto
import com.example.hermes_travelapp.domain.model.Hotel
import com.example.hermes_travelapp.domain.model.HotelReservation
import com.example.hermes_travelapp.domain.model.HotelRoom

fun HotelDto.toDomain(): Hotel {
    val baseUrl = "http://15.224.84.148:8090"
    val fullImageUrl = if (imageUrl?.startsWith("/") == true) {
        "$baseUrl$imageUrl"
    } else if (imageUrl != null && !imageUrl.startsWith("http")) {
        "$baseUrl/$imageUrl"
    } else {
        imageUrl ?: ""
    }

    return Hotel(
        id = id ?: "",
        name = name ?: "Hotel sin nombre",
        address = address ?: "Dirección no disponible",
        rating = rating ?: 0,
        imageUrl = fullImageUrl,
        rooms = rooms?.map { it.toDomain() } ?: emptyList()
    )
}

fun RoomDto.toDomain(): HotelRoom {
    val baseUrl = "http://15.224.84.148:8090"
    val domainImages = images?.map { img ->
        if (img.startsWith("/")) "$baseUrl$img"
        else if (!img.startsWith("http")) "$baseUrl/$img"
        else img
    } ?: emptyList()

    return HotelRoom(
        id = id ?: "",
        roomType = roomType ?: "Estándar",
        price = price ?: 0.0,
        images = domainImages
    )
}

fun ReservationDto.toDomain(): HotelReservation {
    return HotelReservation(
        id = id,
        hotelId = hotelId,
        roomId = roomId,
        startDate = startDate,
        endDate = endDate,
        guestName = guestName,
        guestEmail = guestEmail,
        hotel = hotel?.toDomain(),
        room = room?.toDomain()
    )
}
