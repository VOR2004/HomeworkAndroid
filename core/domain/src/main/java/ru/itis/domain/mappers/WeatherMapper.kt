package ru.itis.domain.mappers

import ru.itis.domain.dto.WeatherResponse
import ru.itis.domain.model.Weather

interface WeatherMapper {
    fun map(response: WeatherResponse): Weather
}