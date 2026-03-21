package com.example.hermes_travelapp.domain.model

/**
 * Represents a suggested travel destination or activity.
 *
 * @property lugar Name of the place or activity.
 * @property tipo Category of the recommendation (e.g., "Restaurant", "Museum").
 * @property pais Country where it is located.
 * @property ciudadRegion City or region within the country.
 * @property precio Estimated price level or cost.
 * @property descripcion Detailed description of what to expect.
 */
data class RecommendationItem(
    val lugar: String,
    val tipo: String,
    val pais: String,
    val ciudadRegion: String,
    val precio: Int,
    val descripcion: String
)
