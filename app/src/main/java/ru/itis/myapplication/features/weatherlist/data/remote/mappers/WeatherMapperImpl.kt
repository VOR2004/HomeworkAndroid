package ru.itis.myapplication.features.weatherlist.data.remote.mappers

import ru.itis.myapplication.features.weatherlist.domain.mappers.WeatherMapper
import ru.itis.myapplication.features.weatherlist.domain.model.Weather
import ru.itis.myapplication.features.weatherlist.data.remote.dto.WeatherResponse
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