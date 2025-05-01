package ru.itis.myapplication.core.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.myapplication.features.weatherlist.data.remote.repository.WeatherRepositoryImpl
import ru.itis.myapplication.features.weatherlist.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherRepositoryBinderModule {

    @Binds
    @Singleton
    fun bindWeatherRepositoryToImpl(impl: WeatherRepositoryImpl): WeatherRepository
}