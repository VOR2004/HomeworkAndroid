package ru.itis.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.domain.model.PushMessage

interface PushRepository {
    suspend fun savePushMessage(message: PushMessage)
    fun getPushMessages(): Flow<List<PushMessage>>
}