package ru.itis.myapplication.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.search.presentation.ui.screens.SearchScreen
import ru.itis.weatherdetails.presentation.ui.screens.WeatherDetailsScreen
import ru.itis.weatherlist.presentation.ui.screens.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Main) {
        composable<Routes.Main> {
            MainScreen(
                onItemClick = { weather ->
                    navController.navigate(Routes.Details(weather.cityName))
                },
                onSearchClick = {
                    navController.navigate(Routes.Search)
                })
        }
        composable<Routes.Details> { backStackEntry ->
            val args = backStackEntry.toRoute<Routes.Details>()
            WeatherDetailsScreen(
                city = args.city,
                onBack = { navController.popBackStack() }
            )
        }
        composable<Routes.Search> {
            SearchScreen(onBack = { navController.popBackStack() })
        }
    }
}

