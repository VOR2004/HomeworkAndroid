package ru.itis.search.data.local.mappers

import ru.itis.data.local.entities.CityWeatherCacheEntity
import ru.itis.domain.model.Weather

interface LocalWeatherCacheMapper {
    fun toEntity(
        weather: Weather,
        key: String,
        lastUpdateTime: Long,
        queriesSinceUpdate: Int
    ): CityWeatherCacheEntity

    fun toDomain(entity: CityWeatherCacheEntity): Weather
}