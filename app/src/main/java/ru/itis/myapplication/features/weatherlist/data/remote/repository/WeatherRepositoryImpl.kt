package ru.itis.myapplication.features.weatherlist.data.remote.repository

import retrofit2.HttpException
import ru.itis.myapplication.core.exceptions.AppException
import ru.itis.myapplication.features.weatherlist.data.remote.api.WeatherApi
import ru.itis.myapplication.features.weatherdetails.domain.mappers.WeatherDetailsMapper
import ru.itis.myapplication.features.weatherlist.domain.mappers.WeatherMapper
import ru.itis.myapplication.features.weatherlist.domain.model.Weather
import ru.itis.myapplication.features.weatherdetails.domain.model.WeatherDetails
import ru.itis.myapplication.features.weatherlist.domain.repository.WeatherRepository
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
        } catch (_: Exception) {
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
        } catch (_: Exception) {
            Result.failure(AppException.Unknown())
        }
    }
}
