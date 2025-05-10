package ru.itis.myapplication.features.weatherlist.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.itis.myapplication.features.weatherlist.data.remote.dto.WeatherResponse

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
    ): WeatherResponse
}