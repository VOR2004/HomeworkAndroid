package ru.itis.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.domain.dto.WeatherResponse

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
    ): WeatherResponse
}