package com.example.hermes_travelapp

import android.app.Activity
import android.content.Context
import java.util.Locale

fun applyLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)
    
    val resources = context.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)
    
    // Create a new context with the updated configuration
    context.createConfigurationContext(configuration)
    
    // Recreate the activity to apply changes immediately
    (context as? Activity)?.recreate()
}
