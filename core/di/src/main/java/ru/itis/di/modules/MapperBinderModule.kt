package ru.itis.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.data.mappers.WeatherDetailsMapperImpl
import ru.itis.data.mappers.WeatherMapperImpl
import ru.itis.domain.mappers.WeatherDetailsMapper
import ru.itis.domain.mappers.WeatherMapper

@Module
@InstallIn(SingletonComponent::class)
interface MapperBinderModule {

    @Binds
    fun bindWeatherMapper(impl: WeatherMapperImpl): WeatherMapper

    @Binds
    fun bindWeatherDetailsMapper(impl: WeatherDetailsMapperImpl): WeatherDetailsMapper
}