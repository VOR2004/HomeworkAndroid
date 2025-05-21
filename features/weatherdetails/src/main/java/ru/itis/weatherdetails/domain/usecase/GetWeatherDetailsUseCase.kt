package ru.itis.weatherdetails.domain.usecase

import ru.itis.domain.repository.WeatherRepository
import ru.itis.domain.model.WeatherDetails
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<WeatherDetails> {
        return repository.getWeatherDetails(city)
    }
}