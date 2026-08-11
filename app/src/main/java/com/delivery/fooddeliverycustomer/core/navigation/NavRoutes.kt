package com.delivery.fooddeliverycustomer.core.navigation

sealed class NavRoutes(val route: String) {

    data object Splash : NavRoutes("splash")

    data object Main : NavRoutes("main")

    data object Login : NavRoutes("login")

    data object Home : NavRoutes("home")

    data object Profile : NavRoutes("profile")
}