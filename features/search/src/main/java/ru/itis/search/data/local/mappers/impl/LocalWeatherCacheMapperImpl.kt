package ru.itis.search.data.local.mappers.impl

import ru.itis.data.local.entities.CityWeatherCacheEntity
import ru.itis.domain.model.Weather
import ru.itis.search.data.local.mappers.LocalWeatherCacheMapper
import javax.inject.Inject

class LocalWeatherCacheMapperImpl @Inject constructor(): LocalWeatherCacheMapper {

    override fun toEntity(
        weather: Weather,
        key: String,
        lastUpdateTime: Long,
        queriesSinceUpdate: Int
    ): CityWeatherCacheEntity = CityWeatherCacheEntity(
        cityKey = key,
        cityName = weather.cityName,
        temperature = weather.temperature,
        description = weather.description,
        lastUpdateTime = lastUpdateTime,
        queriesSinceUpdate = queriesSinceUpdate
    )

    override fun toDomain(entity: CityWeatherCacheEntity): Weather = Weather(
        cityName = entity.cityName,
        temperature = entity.temperature,
        description = entity.description
    )
}