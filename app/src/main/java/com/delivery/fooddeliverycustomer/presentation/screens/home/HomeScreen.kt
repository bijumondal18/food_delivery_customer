package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.delivery.fooddeliverycustomer.core.components.LocationBottomSheet
import com.delivery.fooddeliverycustomer.domain.model.foodCategories
import com.delivery.fooddeliverycustomer.domain.model.restaurant.DiscountType
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Offer
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant


val restaurants = listOf(

    Restaurant(
        id = "restaurant_001",
        name = "Food Palace",
        description = "Indian, Biryani",
        logo = null,
        images = listOf(
            "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4",
            "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f"
        ),
        cuisines = listOf(
            "Indian",
            "Biryani"
        ),
        rating = 4.5,
        totalRatings = 1250,
        deliveryFee = 30.0,
        minimumOrderAmount = 199.0,
        isOpen = true,
        isPureVeg = false,
        isPromoted = true,
    ),

    Restaurant(
        id = "restaurant_002",
        name = "Pizza Hub",
        description = "Pizza, Italian",
        images = listOf(
            "https://images.unsplash.com/photo-1513104890138-7c749659a591",
            "https://images.unsplash.com/photo-1574071318508-1cdbab80d002"
        ),
        cuisines = listOf(
            "Pizza",
            "Italian"
        ),
        rating = 4.4,
        totalRatings = 980,
        deliveryFee = 25.0,
        minimumOrderAmount = 249.0,
        isOpen = true,
        isPureVeg = false,
    ),

    Restaurant(
        id = "restaurant_003",
        name = "Burger House",
        description = "Burgers, Fast Food",
        images = listOf(
            "https://images.unsplash.com/photo-1568901346375-23c9450c58cd",
            "https://images.unsplash.com/photo-1571091718767-18b5b1457add"
        ),
        cuisines = listOf(
            "Burgers",
            "Fast Food"
        ),
        rating = 4.3,
        totalRatings = 750,
        deliveryFee = 20.0,
        minimumOrderAmount = 149.0,
        isOpen = true,
        isPureVeg = false,
    ),

    Restaurant(
        id = "restaurant_004",
        name = "Spice Garden",
        description = "Indian, North Indian",
        images = listOf(
            "https://images.unsplash.com/photo-1515003197210-e0cd71810b5f",
            "https://images.unsplash.com/photo-1601050690597-df0568f70950"
        ),
        cuisines = listOf(
            "Indian",
            "North Indian"
        ),
        rating = 4.6,
        totalRatings = 1520,
        deliveryFee = 30.0,
        minimumOrderAmount = 199.0,
        isOpen = true,
        isPureVeg = true,
    ),

    Restaurant(
        id = "restaurant_005",
        name = "Dragon Wok",
        description = "Chinese, Asian",
        images = listOf(
            "https://images.unsplash.com/photo-1512058564366-18510be2db19",
            "https://images.unsplash.com/photo-1563245372-f21724e3856d"
        ),
        cuisines = listOf(
            "Chinese",
            "Asian"
        ),
        rating = 4.2,
        totalRatings = 620,
        deliveryFee = 35.0,
        minimumOrderAmount = 299.0,
        isOpen = true,
        isPureVeg = false,
    ),

    Restaurant(
        id = "restaurant_006",
        name = "Sweet Treats",
        description = "Desserts, Bakery",
        images = listOf(
            "https://images.unsplash.com/photo-1551024506-0bccd828d307",
            "https://images.unsplash.com/photo-1578985545062-69928b1d9587"
        ),
        cuisines = listOf(
            "Desserts",
            "Bakery"
        ),
        rating = 4.7,
        totalRatings = 2100,
        deliveryFee = 15.0,
        minimumOrderAmount = 99.0,
        isOpen = true,
        isPureVeg = true,
    )
)


@Composable
fun HomeScreen(
    state: HomeUiState,
    viewModel: HomeViewModel,
    onNavigateToSearch: () -> Unit,
    onNavigateToProfile: () -> Unit,
) {

    val listState = rememberLazyListState()


    var showLocationSheet by remember {
        mutableStateOf(false)
    }

    var showLoginSheet by remember {
        mutableStateOf(false)
    }

    var selectedCategory by rememberSaveable {
        mutableStateOf("All")
    }

    var contentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        contentVisible = true
    }


    LaunchedEffect(Unit) {
        viewModel.loadLocation()
    }


    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            3
        }
    )

    AnimatedVisibility(
        visible = contentVisible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 350,
                easing = FastOutSlowInEasing
            )
        ) + slideInVertically(
            initialOffsetY = { 20 },
            animationSpec = tween(durationMillis = 350, easing = FastOutSlowInEasing)
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                )
                .statusBarsPadding(),
            state = listState
        ) {
            item {
                HomeLocationHeader(
                    location = state.location?.address
                        ?: "Fetching location...",
                    onLocationClick = {
                        showLocationSheet = true
                    },
                    profileImageUrl = "",
                    onProfileClick = onNavigateToProfile,
                    onLoginClick = {},
                    isLoggedIn = true
                )
            }

            stickyHeader {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.background,
                        shadowElevation = if (
                            listState.firstVisibleItemIndex > 0
                        ) {
                            2.dp
                        } else {
                            0.dp
                        }
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 10.dp
                                )
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.outline,
                                    shape = CircleShape
                                )
                                .clickable {
                                    onNavigateToSearch()
                                }
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = CircleShape
                                )
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 13.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(
                                modifier = Modifier.width(10.dp)
                            )

                            Text(
                                text = "Search food, restaurants...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }


                    // Categories

                    CategorySection(
                        categories = _root_ide_package_.com.delivery.fooddeliverycustomer.domain.model.foodCategories,
                        selectedCategory = selectedCategory,
                        onSeeAllClick = {},
                        onCategoryClick = {category ->
                            selectedCategory =
                                category?.name ?: "All"
                        }
                    )

                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )

                }
            }

            // Recommended With Deals Section
            item {
                HomeKitchenSection(
                    onKitchenClick = { kitchen ->
                        // Navigate to home kitchen details
                    }
                )
            }

            item{
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }

            // Popular Restaurants
            item {
                RestaurantSection(
                    restaurants = restaurants,
                    onSeeAllClick = {},
                    onRestaurantClick = {}
                )
            }

            item{
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 10.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }

            item {
                AllRestaurantSection (
                    restaurants =restaurants,
                    onRestaurantClick = {}
                )
            }


            // Footer
            item {
                HomeFooterSection(
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        // ======================================================
        // Location Bottom Sheet
        // IMPORTANT: Outside LazyColumn
        // ======================================================

        if (showLocationSheet) {

            LocationBottomSheet(
                currentLocation = state.location?.address,

                onDismiss = {
                    showLocationSheet = false
                },

                onUseCurrentLocation = {

                    showLocationSheet = false

                    viewModel.loadLocation()
                },

                onAddNewAddress = {

                    showLocationSheet = false

                    // TODO:
                    // Navigate to Add Address screen
                },

                onLocationSelected = { location ->

                    showLocationSheet = false

                    // TODO:
                    // Save/select location
                }
            )
        }

    }
}