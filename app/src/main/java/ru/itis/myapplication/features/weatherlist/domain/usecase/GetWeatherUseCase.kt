package ru.itis.myapplication.features.weatherlist.domain.usecase

import ru.itis.myapplication.features.weatherlist.domain.model.Weather
import ru.itis.myapplication.features.weatherlist.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Result<Weather> {
        return repository.getWeather(city)
    }
}