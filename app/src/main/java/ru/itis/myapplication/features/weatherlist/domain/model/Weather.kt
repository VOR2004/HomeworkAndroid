package ru.itis.myapplication.features.weatherlist.domain.model

data class Weather(
    val cityName: String,
    val temperature: Double,
    val description: String
)