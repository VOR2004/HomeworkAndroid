package ru.itis.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.itis.data.local.entities.CityWeatherCacheEntity

@Dao
interface CityWeatherCacheDao {
    @Query("SELECT * FROM city_weather_cache WHERE cityKey = :key")
    suspend fun getByCityKey(key: String): CityWeatherCacheEntity?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(entity: CityWeatherCacheEntity)

    @Query("UPDATE city_weather_cache SET queriesSinceUpdate = :count WHERE cityKey = :key")
    suspend fun updateQueriesSinceUpdate(key: String, count: Int)

    @Query("UPDATE city_weather_cache SET queriesSinceUpdate = queriesSinceUpdate + 1 WHERE cityKey != :currentKey")
    suspend fun incrementQueriesForOthers(currentKey: String)
}