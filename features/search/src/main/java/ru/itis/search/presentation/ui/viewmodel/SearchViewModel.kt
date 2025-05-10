package ru.itis.search.presentation.ui.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.presentation.ui.viewmodel.BaseViewModel
import ru.itis.search.domain.SearchWeatherUseCase
import ru.itis.search.presentation.ui.screenstates.SearchState
import ru.itis.search.utils.MessageConstants
import ru.itis.utils.constants.ErrorConstants
import ru.itis.utils.exceptions.AppException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchWeatherUseCase: SearchWeatherUseCase,
    private val application: Application
) : BaseViewModel<SearchState>(SearchState()) {

    fun onQueryChange(newQuery: String) {
        viewState = viewState.copy(query = newQuery)
    }

    fun search() {
        val city = viewState.query.trim()
        if (city.isEmpty()) return

        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true, error = null, weather = null)

            val resultWithSource = searchWeatherUseCase(city)

            resultWithSource.result.onSuccess { weather ->
                viewState = viewState.copy(
                    weather = weather,
                    isLoading = false,
                    lastResultFromCache = resultWithSource.fromCache
                )
                showToast(
                    if (resultWithSource.fromCache) MessageConstants.CACHED else MessageConstants.FROM_API
                )
            }.onFailure { throwable ->
                val errorMessage = when (throwable) {
                    is AppException.UnknownHost -> ErrorConstants.NO_CONNECTION
                    is AppException.Unauthorized -> ErrorConstants.UNAUTHORIZED
                    is AppException.NotFound -> ErrorConstants.NOT_FOUND
                    is AppException.ServerError -> ErrorConstants.SERVER_ERROR
                    else -> ErrorConstants.UNKNOWN_ERROR
                }
                viewState = viewState.copy(
                    isLoading = false,
                    error = errorMessage
                )
            }
        }
    }

    fun dismissError() {
        viewState = viewState.copy(error = null)
    }

    private fun showToast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }
}
