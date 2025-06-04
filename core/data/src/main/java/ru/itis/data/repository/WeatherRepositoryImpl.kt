package ru.itis.data.repository

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import retrofit2.HttpException
import ru.itis.data.api.WeatherApi
import ru.itis.domain.mappers.WeatherDetailsMapper
import ru.itis.domain.mappers.WeatherMapper
import ru.itis.domain.model.Weather
import ru.itis.domain.model.WeatherDetails
import ru.itis.domain.repository.WeatherRepository
import ru.itis.utils.constants.CrashlyticsKeys
import ru.itis.utils.exceptions.AppException
import java.net.UnknownHostException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val weatherMapper: WeatherMapper,
    private val weatherDetailsMapper: WeatherDetailsMapper
) : WeatherRepository {
    override suspend fun getWeather(city: String): Result<Weather> {
        return try {
            val response = api.getWeather(city)
            Result.success(weatherMapper.map(response))
        } catch (_: UnknownHostException) {
            Result.failure(AppException.UnknownHost())
        } catch (e: HttpException) {
            Result.failure(
                when (e.code()) {
                    401 -> AppException.Unauthorized()
                    404 -> AppException.NotFound()
                    in 500..599 -> AppException.ServerError()
                    else -> AppException.Unknown()
                }
            )
        } catch (e: Exception) {
            Firebase.crashlytics.setCustomKey(CrashlyticsKeys.CITY, city)
            Firebase.crashlytics.recordException(e)
            Result.failure(AppException.Unknown())
        }
    }

    override suspend fun getWeatherDetails(city: String): Result<WeatherDetails> {
        return try {
            val response = api.getWeather(city)
            Result.success(weatherDetailsMapper.map(response))
        } catch(_: UnknownHostException) {
            Result.failure(AppException.UnknownHost())
        } catch (e: HttpException) {
            Result.failure(when (e.code()) {
                401 -> AppException.Unauthorized()
                404 -> AppException.NotFound()
                in 500..599 -> AppException.ServerError()
                else -> AppException.Unknown()
            }
            )
        } catch (e: Exception) {
            Firebase.crashlytics.setCustomKey(CrashlyticsKeys.CITY, city)
            Firebase.crashlytics.recordException(e)
            Result.failure(AppException.Unknown())
        }
    }
}