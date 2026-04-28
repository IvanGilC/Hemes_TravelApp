package com.example.hermes_travelapp.data.repository

import android.util.Log
import com.example.hermes_travelapp.data.database.dao.TripDao
import com.example.hermes_travelapp.data.database.mapper.toDomain
import com.example.hermes_travelapp.data.database.mapper.toEntity
import com.example.hermes_travelapp.domain.model.Trip
import com.example.hermes_travelapp.domain.repository.AuthRepository
import com.example.hermes_travelapp.domain.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of the [TripRepository] interface.
 * This class acts as a bridge between the domain layer and the data source,
 * delegating all operations to [TripDao].
 */
@Singleton
class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
    private val authRepository: AuthRepository
) : TripRepository {

    private companion object {
        const val TAG = "TripRepository"
    }

    override fun getTrips(): Flow<List<Trip>> {
        val userId = authRepository.getCurrentUserId() ?: "default_user"
        Log.d(TAG, "getTrips called: fetching trips for userId=$userId")
        return tripDao.getTripsByUser(userId)
            .map { list ->
                Log.d(TAG, "getTrips returned ${list.size} trips for userId=$userId")
                list.map { it.toDomain() }
            }
    }

    override suspend fun addTrip(trip: Trip) {
        val userId = authRepository.getCurrentUserId() ?: "default_user"
        Log.d(TAG, "addTrip called with title='${trip.title}', userId=$userId")
        tripDao.insertTrip(trip.toEntity(userId))
        Log.i(TAG, "addTrip successful: trip '${trip.title}' added for user $userId.")
    }

    override suspend fun editTrip(trip: Trip) {
        val userId = authRepository.getCurrentUserId() ?: "default_user"
        Log.d(TAG, "editTrip called for id=${trip.id}, userId=$userId")
        tripDao.updateTrip(trip.toEntity(userId))
        Log.i(TAG, "editTrip successful: trip id=${trip.id} updated for user $userId.")
    }

    override suspend fun deleteTrip(tripId: String) {
        Log.d(TAG, "deleteTrip called for tripId=$tripId")
        tripDao.deleteTripById(tripId)
        Log.i(TAG, "deleteTrip successful: tripId=$tripId removed.")
    }
}
