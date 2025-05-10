package ru.itis.search.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.search.data.local.mappers.LocalWeatherCacheMapper
import ru.itis.search.data.local.mappers.impl.LocalWeatherCacheMapperImpl

@Module
@InstallIn(SingletonComponent::class)
interface MapperBinderModule {

    @Binds
    fun bindLocalCacheMapper_toImpl(impl: LocalWeatherCacheMapperImpl): LocalWeatherCacheMapper
}