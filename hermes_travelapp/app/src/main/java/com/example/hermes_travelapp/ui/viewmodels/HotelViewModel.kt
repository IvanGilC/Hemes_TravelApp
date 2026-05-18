package com.example.hermes_travelapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hermes_travelapp.data.PreferencesManager
import com.example.hermes_travelapp.domain.model.Hotel
import com.example.hermes_travelapp.domain.repository.HotelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val repository: HotelRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private companion object {
        const val TAG = "HotelViewModel"
    }

    // Form State
    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city.asStateFlow()

    private val _startDate = MutableStateFlow("")
    val startDate: StateFlow<String> = _startDate.asStateFlow()

    private val _endDate = MutableStateFlow("")
    val endDate: StateFlow<String> = _endDate.asStateFlow()

    private val _maxPrice = MutableStateFlow(500f)
    val maxPrice: StateFlow<Float> = _maxPrice.asStateFlow()

    private val _stars = MutableStateFlow(0)
    val stars: StateFlow<Int> = _stars.asStateFlow()

    // Validation State
    private val _cityError = MutableStateFlow<String?>(null)
    val cityError: StateFlow<String?> = _cityError.asStateFlow()

    private val _startDateError = MutableStateFlow<String?>(null)
    val startDateError: StateFlow<String?> = _startDateError.asStateFlow()

    private val _endDateError = MutableStateFlow<String?>(null)
    val endDateError: StateFlow<String?> = _endDateError.asStateFlow()

    // Search Results State
    private val _availableHotels = MutableStateFlow<List<Hotel>>(emptyList())
    val availableHotels: StateFlow<List<Hotel>> = _availableHotels.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    // Reservation State
    private val _isReserving = MutableStateFlow(false)
    val isReserving: StateFlow<Boolean> = _isReserving.asStateFlow()

    private val _reservationSuccess = MutableStateFlow(false)
    val reservationSuccess: StateFlow<Boolean> = _reservationSuccess.asStateFlow()

    // Form Setters
    fun onCitySelected(city: String) {
        _city.value = city
        _cityError.value = null
    }

    fun onStartDateSelected(date: String) {
        _startDate.value = date
        _startDateError.value = null
    }

    fun onEndDateSelected(date: String) {
        _endDate.value = date
        _endDateError.value = null
    }

    fun onMaxPriceChanged(price: Float) {
        _maxPrice.value = price
    }

    fun onStarsChanged(stars: Int) {
        _stars.value = stars
    }

    /**
     * Searches for available hotels based on city and dates.
     */
    fun searchHotels(onSuccess: () -> Unit = {}) {
        val currentCity = _city.value
        val start = _startDate.value
        val end = _endDate.value

        if (!validate(currentCity, start, end)) return

        Log.d(TAG, "searchHotels: Searching for hotels in $currentCity from $start to $end")
        _isLoading.value = true
        _errorMessage.value = null

        val apiStartDate = convertDateFormat(start)
        val apiEndDate = convertDateFormat(end)

        viewModelScope.launch {
            repository.checkAvailability(
                groupId = "G03",
                city = currentCity,
                startDate = apiStartDate,
                endDate = apiEndDate
            ).onSuccess { hotels ->
                Log.d(TAG, "searchHotels: Successfully found ${hotels.size} hotels")
                _availableHotels.value = hotels
                _isLoading.value = false
                onSuccess()
            }.onFailure { exception ->
                Log.e(TAG, "searchHotels: Error searching hotels: ${exception.message}")
                _errorMessage.value = exception.message ?: "Error al buscar hoteles"
                _isLoading.value = false
            }
        }
    }

    private fun validate(city: String, start: String, end: String): Boolean {
        var isValid = true
        _cityError.value = null
        _startDateError.value = null
        _endDateError.value = null

        if (city.isBlank()) {
            _cityError.value = "La ciudad es obligatoria"
            isValid = false
        }
        if (start.isBlank()) {
            _startDateError.value = "La fecha de entrada es obligatoria"
            isValid = false
        }
        if (end.isBlank()) {
            _endDateError.value = "La fecha de salida es obligatoria"
            isValid = false
        }

        if (start.isNotBlank() && end.isNotBlank()) {
            try {
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val startObj = sdf.parse(start)
                val endObj = sdf.parse(end)
                if (startObj != null && endObj != null && !startObj.before(endObj)) {
                    _endDateError.value = "La entrada debe ser anterior a la salida"
                    isValid = false
                }
            } catch (e: Exception) {
                _endDateError.value = "Formato de fecha inválido"
                isValid = false
            }
        }
        return isValid
    }

    private fun convertDateFormat(date: String): String {
        return try {
            val inputSdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val outputSdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateObj = inputSdf.parse(date)
            if (dateObj != null) outputSdf.format(dateObj) else date
        } catch (e: Exception) {
            date
        }
    }

    fun clearError() {
        Log.d(TAG, "clearError: Clearing errors")
        _errorMessage.value = null
        _cityError.value = null
        _startDateError.value = null
        _endDateError.value = null
    }

    /**
     * Confirms a hotel reservation.
     */
    fun confirmReservation(
        hotelId: String,
        roomId: String,
        startDate: String,
        endDate: String,
        onSuccess: () -> Unit = {}
    ) {
        val guestName = preferencesManager.username
        val guestEmail = preferencesManager.email

        Log.d(TAG, "confirmReservation: hotelId=$hotelId, roomId=$roomId, guest=$guestName")
        
        if (guestName.isBlank() || guestEmail.isBlank()) {
            _errorMessage.value = "Datos de usuario incompletos en el perfil"
            return
        }

        _isReserving.value = true
        _errorMessage.value = null
        _reservationSuccess.value = false

        val apiStartDate = convertDateFormat(startDate)
        val apiEndDate = convertDateFormat(endDate)

        viewModelScope.launch {
            repository.reserveRoom(
                groupId = "G03",
                hotelId = hotelId,
                roomId = roomId,
                startDate = apiStartDate,
                endDate = apiEndDate,
                guestName = guestName,
                guestEmail = guestEmail
            ).onSuccess {
                Log.d(TAG, "confirmReservation: Success")
                _isReserving.value = false
                _reservationSuccess.value = true
                onSuccess()
            }.onFailure { exception ->
                Log.e(TAG, "confirmReservation: Error: ${exception.message}")
                _errorMessage.value = exception.message ?: "Error al realizar la reserva"
                _isReserving.value = false
            }
        }
    }

    fun resetReservationState() {
        _reservationSuccess.value = false
        _errorMessage.value = null
    }
}
