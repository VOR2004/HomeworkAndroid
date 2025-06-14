package ru.itis.search.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.itis.search.data.remote.repository.SearchRepository
import ru.itis.search.domain.SearchWeatherUseCase

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    fun provideSearchWeatherUseCase(repository: SearchRepository): SearchWeatherUseCase {
        return SearchWeatherUseCase(repository)
    }
}