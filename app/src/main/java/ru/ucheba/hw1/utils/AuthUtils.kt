package ru.ucheba.hw1.utils

import java.security.SecureRandom
import java.util.Base64
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


object AuthUtils {
    fun checkEmail(email: String): Boolean {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex())
    }

    fun checkPassword(password: String): Boolean {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{5,}$".toRegex())
    }

    fun checkNickname(nickname: String): Boolean {
        return nickname.matches("^[a-zA-Z0-9_]{3,15}$".toRegex())
    }

    fun checkIfEmail(usernameOrEmail: String): Boolean {
        return usernameOrEmail.contains("@")
    }

    fun isValidUsernameOrEmail(usernameOrEmail: String): Boolean {
        return if (checkIfEmail(usernameOrEmail)) {
            checkEmail(usernameOrEmail)
        } else {
            checkNickname(usernameOrEmail)
        }
    }

    private const val SALT_LENGTH = 16
    private const val ITERATIONS = 65536
    private const val KEY_LENGTH = 256
    private const val ALGORITHM = "PBKDF2WithHmacSHA256"

    fun hashPasswordPBKDF2(rawPassword: String?): String {
        if (rawPassword == null) {
            throw IllegalArgumentException("Password cannot be null")
        }

        val salt = generateSalt()
        val hash = generateHash(rawPassword, salt)
        return "$salt:$hash"
    }

    fun verifyPasswordPBKDF2(rawPassword: String?, hashedPassword: String?): Boolean {
        if (rawPassword == null || hashedPassword == null) {
            return false
        }

        val parts = hashedPassword.split(":")
        if (parts.size != 2) {
            return false
        }

        val salt = parts[0]
        val hash = parts[1]

        val calculatedHash = generateHash(rawPassword, salt)
        return hash == calculatedHash
    }


    private fun generateSalt(): String {
        val random = SecureRandom()
        val salt = ByteArray(SALT_LENGTH)
        random.nextBytes(salt)
        return Base64.getEncoder().encodeToString(salt)
    }


    private fun generateHash(password: String, salt: String): String {
        val saltBytes = Base64.getDecoder().decode(salt)
        val spec = PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH)
        val skf = SecretKeyFactory.getInstance(ALGORITHM)
        val hash = skf.generateSecret(spec).encoded
        return Base64.getEncoder().encodeToString(hash)
    }
}