package ru.itis.weatherlist.domain.usecase

import ru.itis.domain.model.Weather
import ru.itis.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<Weather> {
        return repository.getWeather(city)
    }
}