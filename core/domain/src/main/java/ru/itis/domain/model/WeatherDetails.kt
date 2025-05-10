package ru.itis.domain.model

data class WeatherDetails(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val pressure: Int,
    val feelsLike: Double,
    val timezone: Int
)