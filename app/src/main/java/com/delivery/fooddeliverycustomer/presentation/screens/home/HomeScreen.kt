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
import com.delivery.fooddeliverycustomer.domain.model.restaurant.RestaurantMockData


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
                        // SearchBar
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
                        categories = foodCategories,
                        selectedCategory = selectedCategory,
                        onSeeAllClick = {},
                        onCategoryClick = { category ->
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
                RecommendedWithDealsSection(
                    restaurant = RestaurantMockData.recommendedRestaurants,
                    onRestaurantClick = { restaurant ->
                        // Navigate to home kitchen details
                    }
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }

            // Nearby Restaurants
            item {
                NearbyRestaurantSection(
                    restaurants = RestaurantMockData.nearbyRestaurants,
                    onRestaurantClick = {}
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }

            item {
                AllRestaurantSection(
                    restaurants = RestaurantMockData.nearbyRestaurants,
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