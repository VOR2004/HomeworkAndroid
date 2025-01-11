package ru.ucheba.hw1.repository

import android.app.NotificationManager
import ru.ucheba.hw1.R
import ru.ucheba.hw1.model.NotificationChannelData

object Constants {

    val notificationsChannelData = listOf(
        NotificationChannelData(
            id = R.string.default_channel_id,
            name = R.string.text_channel_default,
            importance = NotificationManager.IMPORTANCE_DEFAULT
        ),
        NotificationChannelData(
            id = R.string.low_channel_id,
            name = R.string.text_channel_low,
            importance = NotificationManager.IMPORTANCE_LOW
        ),
        NotificationChannelData(
            id = R.string.private_channel_id,
            name = R.string.text_channel_private,
            importance = NotificationManager.IMPORTANCE_HIGH
        ),
        NotificationChannelData(
            id = R.string.urgent_channel_id,
            name = R.string.text_channel_urgent,
            importance = NotificationManager.IMPORTANCE_MAX
        ),
    )
}