package com.example.hermes_travelapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hermes_travelapp.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    object Success : AuthUiState()
    data class Error(val errorCode: String) : AuthUiState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = AuthUiState.Error("ERROR_EMPTY_FIELDS")
            return
        }
        
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.signIn(email, password)
            result.fold(
                onSuccess = {
                    _uiState.value = AuthUiState.Success
                },
                onFailure = { 
                    _uiState.value = AuthUiState.Error("ERROR_INVALID_CREDENTIALS")
                }
            )
        }
    }

    fun register(
        username: String,
        birthDate: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        if (username.isBlank()) {
            _uiState.value = AuthUiState.Error("ERROR_EMPTY_USERNAME")
            return
        }
        if (birthDate.isBlank()) {
            _uiState.value = AuthUiState.Error("ERROR_EMPTY_BIRTHDATE")
            return
        }
        if (email.isBlank()) {
            _uiState.value = AuthUiState.Error("ERROR_EMPTY_EMAIL")
            return
        }
        if (password.isBlank()) {
            _uiState.value = AuthUiState.Error("ERROR_EMPTY_PASSWORD")
            return
        }
        if (confirmPassword.isBlank()) {
            _uiState.value = AuthUiState.Error("ERROR_EMPTY_CONFIRM_PASSWORD")
            return
        }
        
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.value = AuthUiState.Error("ERROR_INVALID_EMAIL")
            return
        }
        
        if (password.length < 6) {
            _uiState.value = AuthUiState.Error("ERROR_WEAK_PASSWORD")
            return
        }
        
        if (password != confirmPassword) {
            _uiState.value = AuthUiState.Error("ERROR_PASSWORD_MISMATCH")
            return
        }

        // Simulación de registro exitoso ya que el repositorio no tiene el metodo aún
        _uiState.value = AuthUiState.Success
    }

    fun signOut() {
        authRepository.signOut()
        _uiState.value = AuthUiState.Idle
    }

    fun isLoggedIn(): Boolean {
        return authRepository.isLoggedIn()
    }
    
    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
}
