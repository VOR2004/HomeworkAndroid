package ru.itis.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val description: String
)