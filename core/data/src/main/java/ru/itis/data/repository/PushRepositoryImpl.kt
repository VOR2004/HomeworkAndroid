package ru.itis.data.repository

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.itis.data.local.dao.PushMessageDao
import ru.itis.data.local.entities.PushMessageEntity
import ru.itis.domain.model.PushMessage
import ru.itis.domain.repository.PushRepository
import ru.itis.utils.constants.CrashlyticsKeys
import javax.inject.Inject

class PushRepositoryImpl @Inject constructor(
    private val dao: PushMessageDao
) : PushRepository {
    override suspend fun savePushMessage(message: PushMessage) {
        Firebase.crashlytics.setCustomKey(CrashlyticsKeys.PUSH_MESSAGE_ID, message.id)
        val entity = PushMessageEntity(
            category = message.category.name,
            data = message.data,
            timestamp = message.timestamp
        )
        dao.insert(entity)
    }

    override fun getPushMessages(): Flow<List<PushMessage>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }
}