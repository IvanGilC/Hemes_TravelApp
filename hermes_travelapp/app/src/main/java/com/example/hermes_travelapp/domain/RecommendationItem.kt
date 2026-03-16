package com.example.hermes_travelapp.domain

data class RecommendationItem(
    val lugar: String,
    val tipo: String,
    val pais: String,
    val ciudadRegion: String,
    val precio: Int,
    val descripcion: String
)
