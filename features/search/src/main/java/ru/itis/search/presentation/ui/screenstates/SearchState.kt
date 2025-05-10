package ru.itis.search.presentation.ui.screenstates

import ru.itis.domain.model.Weather

data class SearchState(
    val query: String = "",
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val lastResultFromCache: Boolean = false
)