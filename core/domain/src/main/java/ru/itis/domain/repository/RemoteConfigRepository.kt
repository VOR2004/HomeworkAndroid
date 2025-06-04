package ru.itis.domain.repository

import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepository {
    val featureEnabledFlow: Flow<Boolean>
    suspend fun fetchAndActivate()
}