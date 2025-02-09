package ru.ucheba.hw1.screens.compose

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import ru.ucheba.hw1.viewmodel.AppViewModel

@Serializable
object SignUpScreen

@Serializable
object SignInScreen

@Serializable
object MainScreen

@Composable
fun App(vm: AppViewModel = viewModel()) {

    val navController = rememberNavController()
    val startDestination: Any = if (vm.isLoggedIn()) MainScreen else SignInScreen

    NavHost(navController, startDestination = startDestination) {
        composable<SignUpScreen> {
            RegistrationScreen(
                onNavigateToSignInScreen = {
                    navController.navigate(
                        route = SignInScreen
                    )
                }, vm = vm,
                onNavigateToMainScreen = {
                    navController.navigate(
                        route = MainScreen
                    )
                }
            )
        }
        composable<SignInScreen> {
            SignInScreen(
                onNavigateToSignUpScreen = {
                    navController.navigate(
                        route = SignUpScreen
                    )
                }, vm = vm,
                onNavigateToMainScreen = {
                    navController.navigate(
                        route = MainScreen
                    )
                }
            )
        }
        composable<MainScreen> {
            MainScreen(
                vm,
                onNavigateToSignInScreen = {
                    navController.navigate(
                        route = SignInScreen
                    )
                }
            )
        }
    }
}
