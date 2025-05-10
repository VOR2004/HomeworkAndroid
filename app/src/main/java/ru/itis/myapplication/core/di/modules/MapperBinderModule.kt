package ru.itis.myapplication.core.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.myapplication.features.weatherdetails.data.remote.mappers.WeatherDetailsMapperImpl
import ru.itis.myapplication.features.weatherlist.data.remote.mappers.WeatherMapperImpl
import ru.itis.myapplication.features.weatherdetails.domain.mappers.WeatherDetailsMapper
import ru.itis.myapplication.features.weatherlist.domain.mappers.WeatherMapper

@Module
@InstallIn(SingletonComponent::class)
interface MapperBinderModule {

    @Binds
    fun bindWeatherMapper(impl: WeatherMapperImpl): WeatherMapper

    @Binds
    fun bindWeatherDetailsMapper(impl: WeatherDetailsMapperImpl): WeatherDetailsMapper
}