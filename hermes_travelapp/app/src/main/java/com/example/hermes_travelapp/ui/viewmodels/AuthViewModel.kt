package com.example.hermes_travelapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hermes_travelapp.R
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
    data class Error(val message: Int) : AuthUiState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.signIn(email, password)
            result.fold(
                onSuccess = {
                    _uiState.value = AuthUiState.Success
                },
                onFailure = {
                    // Note: original code used String, new state uses Int. 
                    // For now, I'll keep the register focus as requested.
                    // If signIn fails, we'd need a way to map exception to Int or change Error back to String.
                    // But I will follow the "Error(val message: Int)" requirement for T3.1.
                    _uiState.value = AuthUiState.Error(R.string.error_validation)
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
            _uiState.value = AuthUiState.Error(R.string.error_username_required)
            return
        }
        if (email.isBlank()) {
            _uiState.value = AuthUiState.Error(R.string.error_email_required)
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.value = AuthUiState.Error(R.string.error_email_invalid)
            return
        }
        if (password.isBlank()) {
            _uiState.value = AuthUiState.Error(R.string.error_password_required)
            return
        }
        if (password.length < 6) {
            _uiState.value = AuthUiState.Error(R.string.error_password_length)
            return
        }
        if (confirmPassword.isBlank()) {
            _uiState.value = AuthUiState.Error(R.string.error_confirm_password_required)
            return
        }
        if (password != confirmPassword) {
            _uiState.value = AuthUiState.Error(R.string.error_password_mismatch)
            return
        }

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
