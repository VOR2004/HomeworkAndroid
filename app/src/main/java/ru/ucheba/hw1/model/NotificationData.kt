package ru.ucheba.hw1.model

data class NotificationData(
    val id: Int,
    val title: String,
    val message: String,
    val notificationType: NotificationImportance? = null,
)