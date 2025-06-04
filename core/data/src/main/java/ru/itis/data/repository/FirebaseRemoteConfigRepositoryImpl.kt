package ru.itis.data.repository

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ru.itis.data.keys.Keys
import ru.itis.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class FirebaseRemoteConfigRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository {

    override val featureEnabledFlow = callbackFlow {
        trySend(remoteConfig.getBoolean(FEATURE_ENABLED_KEY))
        awaitClose {}
    }

    override suspend fun fetchAndActivate() {
        try {
            remoteConfig.fetchAndActivate().await()
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
        }
    }

    companion object {
        private const val FEATURE_ENABLED_KEY = Keys.KEY_FEATURE_ENABLED
    }
}
