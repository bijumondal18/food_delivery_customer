package com.delivery.fooddeliverycustomer.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.presentation.cart.CartScreen
import com.delivery.fooddeliverycustomer.presentation.home.HomeScreen
import com.delivery.fooddeliverycustomer.presentation.order.OrderScreen
import com.delivery.fooddeliverycustomer.presentation.profile.ProfileScreen

private data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
)

sealed class BottomNavRoute(
    val route: String,
    val title: String,
    val icon: Int
) {

    data object Home : BottomNavRoute(
        route = "home",
        title = "Home",
        icon = R.drawable.home_24px
    )

    data object Cart : BottomNavRoute(
        route = "cart",
        title = "Cart",
        icon = R.drawable.shopping_cart_24px
    )

    data object Orders : BottomNavRoute(
        route = "orders",
        title = "Orders",
        icon = R.drawable.order_approve_24px
    )

    data object Profile : BottomNavRoute(
        route = "profile",
        title = "Profile",
        icon = R.drawable.person_3_24px
    )
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentDestination =
        navBackStackEntry?.destination

    val bottomItems = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Cart,
        BottomNavRoute.Orders,
        BottomNavRoute.Profile
    )

    Scaffold(

        bottomBar = {

            NavigationBar {

                bottomItems.forEach { item ->

                    val selected =
                        currentDestination
                            ?.hierarchy
                            ?.any { destination ->

                                destination.route == item.route

                            } == true

                    NavigationBarItem(

                        selected = selected,

                        onClick = {

                            navController.navigate(
                                item.route
                            ) {

                                popUpTo(
                                    navController.graph
                                        .findStartDestination()
                                        .id
                                ) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        icon = {

                            Icon(
                                painter = painterResource(
                                    id = item.icon
                                ),
                                contentDescription = item.title,

                                tint = if (selected) {
                                    MaterialTheme
                                        .colorScheme
                                        .primary
                                } else {
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                                }
                            )
                        },

                        label = {
                            Text(item.title)
                        }
                    )
                }
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = BottomNavRoute.Home.route,
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {

            composable(
                BottomNavRoute.Home.route
            ) {
                HomeScreen(
                    showLoginSheet = false,
                    onDismissLoginSheet = {},
                    onGoogleLogin = {},
                    onPhoneLogin = {},
                )
            }

            composable(
                BottomNavRoute.Cart.route
            ) {
                CartScreen()
            }

            composable(
                BottomNavRoute.Orders.route
            ) {
                OrderScreen()
            }

            composable(
                BottomNavRoute.Profile.route
            ) {
                ProfileScreen()
            }
        }
    }
}