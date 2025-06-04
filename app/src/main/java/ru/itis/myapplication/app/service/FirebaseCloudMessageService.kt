package ru.itis.myapplication.app.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.itis.domain.model.PushMessage
import ru.itis.domain.repository.PushRepository
import ru.itis.myapplication.R
import ru.itis.myapplication.app.utils.event.NavigationEvent
import ru.itis.myapplication.app.navigation.Routes
import ru.itis.myapplication.app.utils.constants.KeyNames
import ru.itis.myapplication.app.utils.constants.RoutesStrings
import ru.itis.myapplication.app.utils.event.EventSender
import ru.itis.utils.enums.PushCategory
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseCloudMessageService : FirebaseMessagingService() {

    @Inject
    lateinit var pushRepository: PushRepository

    private val chanelId = KeyNames.HIGH_PRIORITY_CHANNEL_ID
    private var notificationId = 0

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val category = remoteMessage.data[KeyNames.CATEGORY_NAME]?.let {
            PushCategory.valueOf(it)
        } ?: PushCategory.HIGH_PRIORITY_NOTIFICATION

        when (category) {
            PushCategory.HIGH_PRIORITY_NOTIFICATION -> handleNotificationPush(remoteMessage)
            PushCategory.SILENT_DATA -> handleSilentPush(remoteMessage)
            PushCategory.NAVIGATION_PUSH -> handleNavigationPush(remoteMessage)
        }
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun handleNotificationPush(remoteMessage: RemoteMessage) {
        val title = remoteMessage.data[KeyNames.TITLE_NAME] ?: remoteMessage.notification?.title
        val messageBody = remoteMessage.data[KeyNames.MESSAGE_NAME] ?: remoteMessage.notification?.body

        if (!title.isNullOrEmpty() && !messageBody.isNullOrEmpty()) {
            showHighPriorityNotification(title, messageBody)
        }
    }

    private fun handleSilentPush(remoteMessage: RemoteMessage) {
        val data = remoteMessage.data[KeyNames.DATA_NAME] ?: return

        CoroutineScope(Dispatchers.IO).launch {
            pushRepository.savePushMessage(
                PushMessage(
                    category = PushCategory.SILENT_DATA,
                    data = data
                )
            )
        }
    }

    private fun handleNavigationPush(remoteMessage: RemoteMessage) {
        val screen = remoteMessage.data[KeyNames.SCREEN_NAME]
        val route = when (screen) {
            RoutesStrings.MAIN -> Routes.Main
            RoutesStrings.SEARCH -> Routes.Search
            RoutesStrings.GRAPHIC -> Routes.Graphic
            RoutesStrings.DETAILS -> {
                val city = remoteMessage.data[KeyNames.CITY_NAME] ?: return
                Routes.Details(city)
            }
            else -> return
        }
        EventSender.send(NavigationEvent.NavigateTo(route))
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, token)
    }


    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showHighPriorityNotification(title: String, messageBody: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
        }
        createNotificationChannel()

        val notificationBuilder = NotificationCompat.Builder(this, chanelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, notificationBuilder.build())
        notificationId++
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = KeyNames.CHANEL_NAME
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(chanelId, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        private const val TAG = KeyNames.SERVICE_TAG
    }
}

