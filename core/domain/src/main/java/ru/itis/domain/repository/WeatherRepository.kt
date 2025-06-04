package ru.itis.domain.repository

import ru.itis.domain.model.Weather
import ru.itis.domain.model.WeatherDetails


interface WeatherRepository {
    suspend fun getWeather(city: String): Result<Weather>

    suspend fun getWeatherDetails(city: String): Result<WeatherDetails>
}