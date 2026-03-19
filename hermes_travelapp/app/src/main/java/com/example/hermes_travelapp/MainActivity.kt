package com.example.hermes_travelapp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.hermes_travelapp.data.PreferencesManager
import com.example.hermes_travelapp.ui.theme.Hermes_travelappTheme
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Carga inicial de preferencias
        val prefsManager = PreferencesManager(this)
        
        // Aplicar el idioma antes de renderizar
        updateLocale(this, prefsManager.language)
        
        enableEdgeToEdge()
        
        setContent {
            // Estado reactivo para el modo oscuro (se lee de preferencias)
            val isDarkMode by remember { mutableStateOf(prefsManager.isDarkMode) }
            
            Hermes_travelappTheme(darkTheme = isDarkMode) {
                NavGraph()
            }
        }
    }

    /**
     * Configura el Locale del contexto para cambiar el idioma de la aplicación.
     */
    private fun updateLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        
        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.setLocale(locale)
        
        // Actualiza la configuración de recursos para que las strings se carguen en el idioma correcto
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    /**
     * Asegura que el idioma se mantenga al recrear la actividad o cambiar la configuración.
     */
    override fun attachBaseContext(newBase: Context) {
        val prefs = PreferencesManager(newBase)
        val locale = Locale(prefs.language)
        val config = Configuration(newBase.resources.configuration)
        config.setLocale(locale)
        val context = newBase.createConfigurationContext(config)
        super.attachBaseContext(context)
    }
}
