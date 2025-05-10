package ru.itis.weatherdetails.presentation.ui.screenstates

import ru.itis.domain.model.WeatherDetails


data class WeatherDetailsState(
    val weatherDetails: WeatherDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)