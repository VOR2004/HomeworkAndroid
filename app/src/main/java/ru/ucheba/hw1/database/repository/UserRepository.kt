package ru.ucheba.hw1.database.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.ucheba.hw1.database.dao.UserDao
import ru.ucheba.hw1.database.entities.UserEntity

class UserRepository(
    private val userDao: UserDao
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun saveUser(userEntity: UserEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            userDao.saveUser(userEntity)
        }
    }

    suspend fun findByEmail(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.doesEmailExist(email)
        }
    }

    suspend fun getId(boolean: Boolean, value: String): String {
        return if (boolean) {
            withContext(Dispatchers.IO) {
                userDao.getIdByEmail(value)
            }
        } else {
            withContext(Dispatchers.IO) {
                userDao.getIdByUsername(value)
            }
        }
    }

    suspend fun getPasswordHash(boolean: Boolean, value: String): String? {
        return if (boolean) {
            withContext(Dispatchers.IO) {
                userDao.getPasswordByEmail(value)
            }
        } else {
            withContext(Dispatchers.IO) {
                userDao.getPasswordByNickname(value)
            }
        }
    }
}