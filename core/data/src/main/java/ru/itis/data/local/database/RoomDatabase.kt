package ru.itis.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.data.local.dao.CityWeatherCacheDao
import ru.itis.data.local.entities.CityWeatherCacheEntity

@Database(entities = [CityWeatherCacheEntity::class], version = 2)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun cityWeatherCacheDao(): CityWeatherCacheDao
}