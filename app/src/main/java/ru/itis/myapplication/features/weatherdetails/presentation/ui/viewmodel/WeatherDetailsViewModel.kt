package ru.itis.myapplication.features.weatherdetails.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.itis.myapplication.core.constants.ErrorConstants
import ru.itis.myapplication.core.exceptions.AppException
import ru.itis.myapplication.core.presentation.ui.viewmodel.BaseViewModel
import ru.itis.myapplication.features.weatherdetails.domain.usecase.GetWeatherDetailsUseCase
import ru.itis.myapplication.features.weatherdetails.presentation.ui.screenstates.WeatherDetailsState
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
) : BaseViewModel<WeatherDetailsState>(WeatherDetailsState()) {

    fun loadWeatherDetails(city: String) {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, error = null)
            delay(1000)
            getWeatherDetailsUseCase(city).onSuccess { weatherDetails ->
                viewState = viewState.copy(weatherDetails = weatherDetails, isLoading = false)
            }.onFailure { throwable ->
                val error = when (throwable) {
                    is AppException.UnknownHost -> ErrorConstants.NO_CONNECTION
                    is AppException.Unauthorized -> ErrorConstants.UNAUTHORIZED
                    is AppException.NotFound -> ErrorConstants.NOT_FOUND
                    is AppException.ServerError -> ErrorConstants.SERVER_ERROR
                    else -> ErrorConstants.UNKNOWN_ERROR
                }
                viewState = viewState.copy(isLoading = false, error = error)
            }
        }
    }


    fun clearError() {
        viewState = viewState.copy(error = null)
    }
}
