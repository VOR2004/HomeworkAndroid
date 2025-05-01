package ru.itis.myapplication.app.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Main : Routes()

    @Serializable
    data class Details(val city: String) : Routes()
}