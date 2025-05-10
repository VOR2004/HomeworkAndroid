package ru.itis.myapplication.features.weatherlist.domain.repository

import ru.itis.myapplication.features.weatherdetails.domain.model.WeatherDetails
import ru.itis.myapplication.features.weatherlist.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String): Result<Weather>

    suspend fun getWeatherDetails(city: String): Result<WeatherDetails>
}