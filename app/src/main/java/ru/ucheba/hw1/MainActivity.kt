package ru.ucheba.hw1

import android.os.Bundle
import android.widget.Toast
import ru.ucheba.hw1.base.BaseActivity
import ru.ucheba.hw1.base.NavigationAction
import ru.ucheba.hw1.key.Keys
import ru.ucheba.hw1.screens.MainPageFragment
import ru.ucheba.hw1.utils.NotificationHandler

class MainActivity : BaseActivity() {

    override val mainContainerId: Int = R.id.main_fragment_container

    var notificationHandler: NotificationHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intent?.extras?.getInt(Keys().notificationOpened)?.let { payloadInt ->
            if (payloadInt == Keys().notificationOpenedValue) {
                Toast.makeText(this, R.string.toast_opened_via_notifications, Toast.LENGTH_SHORT).show()
            }
        }
        if (notificationHandler == null) {
            notificationHandler = NotificationHandler(this.applicationContext)
        }

        if (savedInstanceState == null) {
            navigate(
                destination = MainPageFragment(),
                destinationTag = MainPageFragment.TAG,
                action = NavigationAction.REPLACE,
            )
        }
    }

    fun changeTheme(theme: Int?) {
        currentTheme = theme ?: R.style.Theme_MyApplication
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationHandler = null
    }

    companion object {
        var currentTheme = R.style.Theme_MyApplication
    }
}