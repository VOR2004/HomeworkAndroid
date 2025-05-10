package ru.itis.search.domain

import ru.itis.domain.model.Weather
import ru.itis.search.data.remote.repository.SearchRepository
import javax.inject.Inject

class SearchWeatherUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(city: String): ResultWithSource<Weather> {
        val result = repository.searchWeather(city)
        val fromCache = repository.isLastResultFromCache()
        return ResultWithSource(result, fromCache)
    }
}

data class ResultWithSource<T>(
    val result: Result<T>,
    val fromCache: Boolean
)