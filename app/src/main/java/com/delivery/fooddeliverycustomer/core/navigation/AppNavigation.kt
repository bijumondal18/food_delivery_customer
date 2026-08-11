package com.delivery.fooddeliverycustomer.core.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delivery.fooddeliverycustomer.presentation.home.HomeScreen
import com.delivery.fooddeliverycustomer.presentation.main.MainScreen
import com.delivery.fooddeliverycustomer.presentation.profile.ProfileScreen
import com.delivery.fooddeliverycustomer.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        composable(
            route = NavRoutes.Splash.route,
        ) {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(NavRoutes.Main.route) {

                        popUpTo(NavRoutes.Splash.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = NavRoutes.Main.route,
        ) {
            MainScreen()
        }

    }
}