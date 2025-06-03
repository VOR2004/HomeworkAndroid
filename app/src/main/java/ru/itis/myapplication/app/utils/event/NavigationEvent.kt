package ru.itis.myapplication.app.utils.event

import ru.itis.myapplication.app.navigation.Routes

sealed class NavigationEvent {
    data class NavigateTo(val route: Routes) : NavigationEvent()
}