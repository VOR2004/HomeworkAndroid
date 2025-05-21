package ru.itis.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather_cache")
data class CityWeatherCacheEntity(
    @PrimaryKey val cityKey: String,
    val cityName: String,
    val temperature: Double,
    val description: String,
    val lastUpdateTime: Long,
    val queriesSinceUpdate: Int
)