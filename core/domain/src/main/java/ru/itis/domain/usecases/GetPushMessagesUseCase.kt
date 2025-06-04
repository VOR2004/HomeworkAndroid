package ru.itis.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.itis.domain.model.PushMessage
import ru.itis.domain.repository.PushRepository
import javax.inject.Inject

class GetPushMessagesUseCase @Inject constructor(
    private val repository: PushRepository
) {
    operator fun invoke(): Flow<List<PushMessage>> {
        return repository.getPushMessages()
    }
}