package ru.itis.myapplication.app.navigation

import MainScreen
import WeatherDetailsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Main) {
        composable<Routes.Main> {
            MainScreen(onItemClick = { weather ->
                navController.navigate(Routes.Details(weather.cityName))
            })
        }
        composable<Routes.Details> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.Details>()
            WeatherDetailsScreen(
                city = args.city,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

