package ru.ucheba.hw1.base

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity: AppCompatActivity() {

    protected abstract val mainContainerId: Int

    fun navigate(
        destination: Fragment,
        destinationTag: String,
        action: NavigationAction = NavigationAction.REPLACE,
        isAddToBackStack: Boolean = true,
        backStackTag: String? = null
        )
    {
        val transaction = supportFragmentManager.beginTransaction()
        when (action) {
            NavigationAction.ADD -> transaction.add(mainContainerId, destination, destinationTag)
            NavigationAction.REPLACE -> transaction.replace(mainContainerId, destination, destinationTag)
            NavigationAction.REMOVE -> transaction.remove(destination)
        }

        if (isAddToBackStack) {
            transaction.addToBackStack(backStackTag)
        }
        transaction.commit()
    }

}