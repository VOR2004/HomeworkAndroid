package ru.itis.search.data.remote.repository

import retrofit2.HttpException
import ru.itis.data.api.WeatherApi
import ru.itis.data.local.dao.CityWeatherCacheDao
import ru.itis.domain.mappers.WeatherMapper
import ru.itis.domain.model.Weather
import ru.itis.search.data.local.mappers.LocalWeatherCacheMapper
import ru.itis.utils.exceptions.AppException
import java.net.UnknownHostException
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: WeatherApi,
    private val dao: CityWeatherCacheDao,
    private val weatherMapper: WeatherMapper,
    private val localWeatherCacheMapper: LocalWeatherCacheMapper
) {
    companion object {
        private const val CACHE_LIFETIME_MS = 5 * 60 * 1000L
        private const val MAX_QUERIES = 3
    }

    private var isLastResultFromCache = false

    suspend fun searchWeather(city: String): Result<Weather> {
        val key = city.trim().lowercase()
        val now = System.currentTimeMillis()
        val cache = dao.getByCityKey(key)

        if (cache != null) {
            val timeSinceUpdate = now - cache.lastUpdateTime
            if (timeSinceUpdate < CACHE_LIFETIME_MS && cache.queriesSinceUpdate < MAX_QUERIES) {
                dao.updateQueriesSinceUpdate(key, 0)
                dao.incrementQueriesForOthers(key)
                isLastResultFromCache = true
                return Result.success(localWeatherCacheMapper.toDomain(cache))
            }
        }

        return try {
            val response = api.getWeather(city)
            val weather = weatherMapper.map(response)

            dao.insert(localWeatherCacheMapper.toEntity(weather, key, now, 0))
            dao.incrementQueriesForOthers(key)

            isLastResultFromCache = false
            Result.success(weather)
        } catch (_: UnknownHostException) {
            if (cache != null) {
                isLastResultFromCache = true
                Result.success(localWeatherCacheMapper.toDomain(cache))
            } else {
                isLastResultFromCache = false
                Result.failure(AppException.UnknownHost())
            }
        } catch (e: HttpException) {
            val appException = when (e.code()) {
                401 -> AppException.Unauthorized()
                404 -> AppException.NotFound()
                in 500..599 -> AppException.ServerError()
                else -> AppException.Unknown()
            }
            if (cache != null) {
                isLastResultFromCache = true
                Result.success(localWeatherCacheMapper.toDomain(cache))
            } else {
                isLastResultFromCache = false
                Result.failure(appException)
            }
        } catch (_: Exception) {
            if (cache != null) {
                isLastResultFromCache = true
                Result.success(localWeatherCacheMapper.toDomain(cache))
            } else {
                isLastResultFromCache = false
                Result.failure(AppException.Unknown())
            }
        }
    }

    fun isLastResultFromCache(): Boolean = isLastResultFromCache
}

