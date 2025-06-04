package ru.itis.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.data.repository.FirebaseRemoteConfigRepositoryImpl
import ru.itis.data.repository.PushRepositoryImpl
import ru.itis.domain.repository.PushRepository
import ru.itis.domain.repository.RemoteConfigRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseRepositoryBinderModule {

    @Binds
    @Singleton
    fun bindPushRepository_toImpl(impl: PushRepositoryImpl): PushRepository

    @Binds
    @Singleton
    fun bindRCRepository_toImpl(impl: FirebaseRemoteConfigRepositoryImpl): RemoteConfigRepository
}