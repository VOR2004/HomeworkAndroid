package ru.ucheba.hw1.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ucheba.hw1.database.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserEntity)
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)")
    fun doesEmailExist(email: String?): Boolean
    @Query("SELECT hash_password FROM users WHERE email = :email")
    fun getPasswordByEmail(email: String?): String?
    @Query("SELECT hash_password FROM users WHERE username = :username")
    fun getPasswordByNickname(username: String?): String?
    @Query("SELECT id FROM users WHERE username = :username")
    fun getIdByUsername(username: String?): String
    @Query("SELECT id FROM users WHERE email = :email")
    fun getIdByEmail(email: String?): String
}