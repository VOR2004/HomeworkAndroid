package ru.ucheba.hw1.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import ru.ucheba.hw1.MainActivity
import ru.ucheba.hw1.R
import ru.ucheba.hw1.key.Keys
import ru.ucheba.hw1.model.NotificationData
import ru.ucheba.hw1.model.NotificationImportance
import ru.ucheba.hw1.repository.Constants

class NotificationHandler(private val appCtx: Context) {

    private val notificationManager =
        appCtx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannelIfNeeded()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannelIfNeeded() {
        Constants.notificationsChannelData.forEach { channelData ->
            if (notificationManager.getNotificationChannel(appCtx.getString(channelData.id)) == null) {
                val channel = with(channelData) {
                    NotificationChannel(appCtx.getString(id), appCtx.getString(name), importance)
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun showNotification(data: NotificationData) {
        val index = when(data.notificationType) {

            NotificationImportance.LOW -> {
                appCtx.resources.getInteger(R.integer.low_channel_id)
            }

            NotificationImportance.MAX -> {
                appCtx.resources.getInteger(R.integer.private_channel_id)
            }

            NotificationImportance.HIGH -> {
                appCtx.resources.getInteger(R.integer.urgent_channel_id)
            }

            else -> {
                appCtx.resources.getInteger(R.integer.default_channel_id)
            }
        }
        val channelId = appCtx.getString(Constants.notificationsChannelData[index].id)

        val activityIntent = Intent(appCtx, MainActivity::class.java).apply {
            putExtra(Keys().notificationOpened, Keys().notificationOpenedValue)
        }

        val pendingIntent = PendingIntent.getActivity(
            appCtx,
            0,
            activityIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder =
            NotificationCompat.Builder(appCtx, channelId)
                .setSmallIcon(R.drawable.baseline_satellite_24)
                .setContentTitle(data.title)
                .setChannelId(channelId)
                .setContentIntent(pendingIntent)

        data.message.let { message ->
            notificationBuilder.setContentText(message)
        }

        notificationManager.notify(data.id, notificationBuilder.build())
    }
}