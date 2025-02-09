package ru.ucheba.hw1.database.session

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import ru.ucheba.hw1.key.KeyNames

class SessionManager(context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        KeyNames.ENCRYPTED_SP_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setLoggedIn(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(KeyNames.KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KeyNames.KEY_IS_LOGGED_IN, false)
    }

    fun setId(id: String?) {
        sharedPreferences.edit().putString(KeyNames.KEY_ID, id).apply()
    }

    fun getId(): String? {
        return sharedPreferences.getString(KeyNames.KEY_ID, null)
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}