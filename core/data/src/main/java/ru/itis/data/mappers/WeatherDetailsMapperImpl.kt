package ru.itis.data.mappers

import ru.itis.domain.dto.WeatherResponse
import ru.itis.domain.mappers.WeatherDetailsMapper
import ru.itis.domain.model.WeatherDetails
import javax.inject.Inject

class WeatherDetailsMapperImpl @Inject constructor(): WeatherDetailsMapper {
    override fun map(response: WeatherResponse): WeatherDetails {
        return WeatherDetails(
            cityName = response.cityName,
            temperature = response.main.temp,
            description = response.weather.firstOrNull()?.description.orEmpty(),
            pressure = response.main.pressure,
            feelsLike = response.main.feelsLike,
            timezone = response.timezone
        )
    }
}