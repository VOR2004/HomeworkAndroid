package ru.itis.domain.mappers

import ru.itis.domain.dto.WeatherResponse
import ru.itis.domain.model.WeatherDetails

interface WeatherDetailsMapper {
    fun map(response: WeatherResponse): WeatherDetails
}