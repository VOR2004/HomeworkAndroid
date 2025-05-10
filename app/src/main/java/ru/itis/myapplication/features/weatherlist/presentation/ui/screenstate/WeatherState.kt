package ru.itis.myapplication.features.weatherlist.presentation.ui.screenstate

import ru.itis.myapplication.features.weatherlist.domain.model.Weather

data class WeatherState(
    val weatherList: List<Weather> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAddCityDialog: Boolean = false
)