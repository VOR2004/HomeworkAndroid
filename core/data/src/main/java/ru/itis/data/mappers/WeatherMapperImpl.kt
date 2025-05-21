package ru.itis.data.mappers

import ru.itis.domain.dto.WeatherResponse
import ru.itis.domain.mappers.WeatherMapper
import ru.itis.domain.model.Weather
import javax.inject.Inject

class WeatherMapperImpl @Inject constructor(): WeatherMapper {
    override fun map(response: WeatherResponse): Weather {
        return Weather(
            cityName = response.cityName,
            temperature = response.main.temp,
            description = response.weather.firstOrNull()?.description.orEmpty()
        )
    }
}