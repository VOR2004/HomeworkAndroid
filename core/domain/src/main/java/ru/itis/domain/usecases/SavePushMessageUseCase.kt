package ru.itis.domain.usecases

import ru.itis.domain.model.PushMessage
import ru.itis.domain.repository.PushRepository
import javax.inject.Inject

class SavePushMessageUseCase @Inject constructor(
    private val repository: PushRepository
) {
    suspend operator fun invoke(message: PushMessage) {
        repository.savePushMessage(message)
    }
}