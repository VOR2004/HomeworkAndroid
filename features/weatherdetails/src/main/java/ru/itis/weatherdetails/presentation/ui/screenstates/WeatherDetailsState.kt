package ru.itis.weatherdetails.presentation.ui.screenstates

import androidx.compose.runtime.Immutable
import ru.itis.domain.model.WeatherDetails

@Immutable
data class WeatherDetailsState(
    val weatherDetails: WeatherDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)