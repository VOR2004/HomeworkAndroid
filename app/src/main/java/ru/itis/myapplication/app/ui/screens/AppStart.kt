package ru.itis.myapplication.app.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import ru.itis.myapplication.app.navigation.SetupNavGraph

@Composable
fun AppStart() {
    val navController = rememberNavController()
    SetupNavGraph(
        navController = navController
    )
}