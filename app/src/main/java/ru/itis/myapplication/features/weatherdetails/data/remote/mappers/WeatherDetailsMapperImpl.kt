package ru.itis.myapplication.features.weatherdetails.data.remote.mappers

import ru.itis.myapplication.features.weatherdetails.domain.mappers.WeatherDetailsMapper
import ru.itis.myapplication.features.weatherdetails.domain.model.WeatherDetails
import ru.itis.myapplication.features.weatherlist.data.remote.dto.WeatherResponse
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