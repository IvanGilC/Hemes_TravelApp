package com.example.hermes_travelapp.data.fakeDB

import androidx.compose.runtime.mutableStateListOf
import com.example.hermes_travelapp.domain.Trip

object FakeTripDataSource {
    val trips = mutableStateListOf<Trip>()
}
