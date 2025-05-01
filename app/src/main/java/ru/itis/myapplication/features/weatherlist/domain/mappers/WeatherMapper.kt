package ru.itis.myapplication.features.weatherlist.domain.mappers

import ru.itis.myapplication.features.weatherlist.domain.model.Weather
import ru.itis.myapplication.features.weatherlist.data.remote.dto.WeatherResponse

interface WeatherMapper {
    fun map(response: WeatherResponse): Weather
}