package com.delivery.fooddeliverycustomer.presentation.home

import android.app.Activity
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.components.FoodCategoryItem
import com.delivery.fooddeliverycustomer.core.components.LocationBottomSheet
import com.delivery.fooddeliverycustomer.core.navigation.NavRoutes
import com.delivery.fooddeliverycustomer.core.ui.theme.AppLightGradient
import com.delivery.fooddeliverycustomer.data.model.FoodCategory
import com.delivery.fooddeliverycustomer.data.model.foodCategories
import com.delivery.fooddeliverycustomer.data.model.restaurants


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
                        categories = foodCategories,
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

            // Nearby Home Kitchens
            item {
                HomeKitchenSection(
                    onSeeAllClick = {
                        // Navigate to all home kitchens
                    },

                    onKitchenClick = { kitchen ->
                        // Navigate to home kitchen details
                    }
                )
            }

            item{
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
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