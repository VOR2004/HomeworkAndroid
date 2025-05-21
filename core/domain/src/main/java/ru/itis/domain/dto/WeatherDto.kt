package ru.itis.domain.dto

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("name") val cityName: String,
    @SerializedName("main") val main: MainData,
    @SerializedName("weather") val weather: List<WeatherDescription>,
    @SerializedName("timezone") val timezone: Int
)

data class MainData(
    @SerializedName("temp") val temp: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("feels_like") val feelsLike: Double
)


data class WeatherDescription(
    @SerializedName("description") val description: String
)