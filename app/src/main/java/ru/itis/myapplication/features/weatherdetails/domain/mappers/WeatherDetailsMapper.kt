package ru.itis.myapplication.features.weatherdetails.domain.mappers

import ru.itis.myapplication.features.weatherlist.data.remote.dto.WeatherResponse
import ru.itis.myapplication.features.weatherdetails.domain.model.WeatherDetails

interface WeatherDetailsMapper {
    fun map(response: WeatherResponse): WeatherDetails
}