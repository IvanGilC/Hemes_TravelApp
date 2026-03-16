package com.example.hermes_travelapp.data.repository

import com.example.hermes_travelapp.data.fakeDB.FakeTripDataSource
import com.example.hermes_travelapp.domain.Trip
import com.example.hermes_travelapp.domain.TripRepository

class TripRepositoryImpl : TripRepository {
    override fun getTrips(): List<Trip> {
        return FakeTripDataSource.trips
    }

    override fun addTrip(trip: Trip) {
        FakeTripDataSource.trips.add(trip)
    }

    override fun editTrip(trip: Trip) {
        val index = FakeTripDataSource.trips.indexOfFirst { it.id == trip.id }
        if (index != -1) {
            FakeTripDataSource.trips[index] = trip
        }
    }

    override fun deleteTrip(tripId: String) {
        FakeTripDataSource.trips.removeAll { it.id == tripId }
    }
}
