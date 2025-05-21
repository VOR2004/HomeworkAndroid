package ru.itis.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.data.repository.WeatherRepositoryImpl
import ru.itis.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherRepositoryBinderModule {

    @Binds
    @Singleton
    fun bindWeatherRepositoryToImpl(impl: WeatherRepositoryImpl): WeatherRepository
}