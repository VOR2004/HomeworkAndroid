package ru.itis.myapplication.features.weatherlist.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.myapplication.core.constants.CityConstants
import ru.itis.myapplication.core.constants.ErrorConstants
import ru.itis.myapplication.core.exceptions.AppException
import ru.itis.myapplication.core.presentation.ui.viewmodel.BaseViewModel
import ru.itis.myapplication.features.weatherlist.domain.model.Weather
import ru.itis.myapplication.features.weatherlist.domain.usecase.GetWeatherUseCase
import ru.itis.myapplication.features.weatherlist.presentation.ui.screenstate.WeatherState
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
) : BaseViewModel<WeatherState>(WeatherState()) {

    private val _cities = MutableStateFlow(CityConstants.CITIES)
    val cities = _cities.asStateFlow()

    fun addCity(city: String) {
        val updated = _cities.value.toMutableList().apply {
            if (city.isNotBlank() && !contains(city)) {
                add(city)
            }
        }
        _cities.value = updated
        loadWeather()
    }

    fun loadWeather() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, error = null)
            val weatherList = mutableListOf<Weather>()
            var error: String? = null

            for (city in cities.value) {
                val result = getWeatherUseCase(city)
                result.onSuccess { weather ->
                    weatherList.add(weather)
                }.onFailure { throwable ->
                    error = when (throwable) {
                        is AppException.UnknownHost -> ErrorConstants.NO_CONNECTION
                        is AppException.Unauthorized -> ErrorConstants.UNAUTHORIZED
                        is AppException.NotFound -> ErrorConstants.NOT_FOUND
                        is AppException.ServerError -> ErrorConstants.SERVER_ERROR
                        else -> ErrorConstants.UNKNOWN_ERROR
                    }
                }
            }

            viewState = viewState.copy(
                weatherList = weatherList,
                isLoading = false,
                error = error
            )
        }
    }


    fun showAddCityDialog() {
        viewState = viewState.copy(showAddCityDialog = true)
    }
    fun hideAddCityDialog() {
        viewState = viewState.copy(showAddCityDialog = false)
    }

    fun clearError() {
        viewState = viewState.copy(error = null)
    }
}