package com.example.hermes_travelapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hermes_travelapp.data.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(preferencesManager.isDarkMode)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    fun toggleDarkMode(enabled: Boolean) {
        _isDarkMode.value = enabled
        preferencesManager.isDarkMode = enabled
    }
}
