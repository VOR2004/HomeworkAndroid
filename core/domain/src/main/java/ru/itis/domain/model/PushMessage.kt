package ru.itis.domain.model

import ru.itis.utils.enums.PushCategory

data class PushMessage(
    val id: Int = 0,
    val category: PushCategory,
    val data: String,
    val timestamp: Long = System.currentTimeMillis()
)