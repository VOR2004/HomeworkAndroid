package ru.itis.myapplication.app.ui.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.collectLatest
import ru.itis.myapplication.app.utils.event.NavigationEvent
import ru.itis.myapplication.app.navigation.SetupNavGraph
import ru.itis.myapplication.app.utils.constants.KeyNames
import ru.itis.myapplication.app.utils.event.EventSender
@Composable
fun AppStart() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val navBackStackEntry = navController.currentBackStackEntryAsState().value

    LaunchedEffect(navBackStackEntry) {
        EventSender.events.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    val currentRoute = navBackStackEntry?.destination
                    if (currentRoute?.hasRoute(event.route::class) == true) {
                        snackBarHostState.showSnackbar(KeyNames.ALREADY_HERE)
                    } else {
                        navController.navigate(event.route)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        SetupNavGraph(navController = navController)
    }
}