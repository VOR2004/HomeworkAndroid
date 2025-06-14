package ru.itis.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.data.local.dao.CityWeatherCacheDao
import ru.itis.data.local.dao.PushMessageDao
import ru.itis.data.local.entities.CityWeatherCacheEntity
import ru.itis.data.local.entities.PushMessageEntity

@Database(
    entities = [
        CityWeatherCacheEntity::class,
        PushMessageEntity::class
    ],
    version = 3
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun cityWeatherCacheDao(): CityWeatherCacheDao
    abstract fun pushMessageDao(): PushMessageDao
}