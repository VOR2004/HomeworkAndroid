package ru.itis.myapplication.features.weatherdetails.presentation.ui.screenstates

import ru.itis.myapplication.features.weatherdetails.domain.model.WeatherDetails

data class WeatherDetailsState(
    val weatherDetails: WeatherDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)