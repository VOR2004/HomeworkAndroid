package ru.itis.myapplication.features.weatherdetails.domain.usecase

import ru.itis.myapplication.features.weatherdetails.domain.model.WeatherDetails
import ru.itis.myapplication.features.weatherlist.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<WeatherDetails> {
        return repository.getWeatherDetails(city)
    }
}