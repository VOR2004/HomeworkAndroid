package ru.itis.weatherlist.presentation.ui.screenstate

import androidx.compose.runtime.Immutable
import ru.itis.domain.model.Weather

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class WeatherState(
    val weatherList: ImmutableList<Weather> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAddCityDialog: Boolean = false,
    val isFeatureEnabled: Boolean = false
)