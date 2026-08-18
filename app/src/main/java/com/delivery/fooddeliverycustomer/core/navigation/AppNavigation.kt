package com.delivery.fooddeliverycustomer.core.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.delivery.fooddeliverycustomer.presentation.cart.CartScreen
import com.delivery.fooddeliverycustomer.presentation.home.HomeScreen
import com.delivery.fooddeliverycustomer.presentation.home.HomeViewModel
import com.delivery.fooddeliverycustomer.presentation.main.MainScreen
import com.delivery.fooddeliverycustomer.presentation.order.OrderScreen
import com.delivery.fooddeliverycustomer.presentation.profile.ProfileScreen
import com.delivery.fooddeliverycustomer.presentation.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route
    ) {

        // -----------------------------------------
        // Splash
        // -----------------------------------------
        composable(
            route = NavRoutes.Splash.route,
        ) {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(NavRoutes.Home.route) {

                        popUpTo(NavRoutes.Splash.route) {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                }
            )
        }

        // -----------------------------------------
        // Home
        // -----------------------------------------
        composable(
            route = NavRoutes.Home.route
        ) {

            val viewModel: HomeViewModel = hiltViewModel()

            val state by viewModel.uiState.collectAsState()

            HomeScreen(
                state = state,
                viewModel = viewModel,
                onNavigateToSearch = {
//                    navController.navigate(
//                        NavRoutes.Search.route
//                    )
                },
                onNavigateToProfile = {
                    navController.navigate(
                        NavRoutes.Profile.route
                    )
                }
            )
        }

        // -----------------------------------------
        // Profile
        // -----------------------------------------
        composable(
            route = NavRoutes.Profile.route
        ) {
            ProfileScreen(
                onBackClick = navController::popBackStack,
                onOrdersClick = {
                    navController.navigate(NavRoutes.Orders.route)
                },
                onCartClick = {
                    navController.navigate(NavRoutes.Cart.route)
                },
                onWishlistClick = {}
            )
        }

        // -----------------------------------------
        // Cart
        // -----------------------------------------
        composable(
            route = NavRoutes.Cart.route
        ) {
            CartScreen(
                onBackClick = navController::popBackStack,
            )
        }

        // -----------------------------------------
        // Wishlist
        // -----------------------------------------
        composable(
            route = NavRoutes.Wishlist.route
        ) {
            CartScreen(

            )
        }

        // -----------------------------------------
        // Orders
        // -----------------------------------------
        composable(
            route = NavRoutes.Orders.route
        ) {
            OrderScreen(
                onBackClick = navController::popBackStack
            )
        }

    }
}