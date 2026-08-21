package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delivery.fooddeliverycustomer.core.components.LocationBottomSheet
import com.delivery.fooddeliverycustomer.core.theme.LightTextSecondary
import com.delivery.fooddeliverycustomer.domain.model.FoodCategory
import com.delivery.fooddeliverycustomer.domain.model.foodCategories
import com.delivery.fooddeliverycustomer.domain.model.restaurant.RestaurantMockData
import com.delivery.fooddeliverycustomer.presentation.widgets.AllRestaurantCard


@Composable
fun HomeScreen(
    state: HomeUiState,
    viewModel: HomeViewModel,
    onNavigateToSearch: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onRestaurantClick: (String) -> Unit
) {

    val listState = rememberLazyListState()

    var showLocationSheet by rememberSaveable {
        mutableStateOf(false)
    }

    var selectedCategory by rememberSaveable {
        mutableStateOf("All")
    }

    /*
     * Load location only when HomeScreen enters composition.
     */
    LaunchedEffect(Unit) {
        viewModel.loadLocation()
    }

    /*
     * Avoid reading LazyListState directly throughout
     * composition.
     */
    val showSearchElevation by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 ||
                    listState.firstVisibleItemScrollOffset > 0
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
            .statusBarsPadding(),
        state = listState
    ) {

        // ============================================================
        // LOCATION HEADER
        // ============================================================

        item(
            key = "location_header"
        ) {

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

        // ============================================================
        // STICKY SEARCH + CATEGORY
        // ============================================================

        stickyHeader(
            key = "search_and_category"
        ) {

            HomeSearchAndCategoryHeader(
                selectedCategory = selectedCategory,
                showElevation = showSearchElevation,

                onSearchClick = onNavigateToSearch,

                onCategoryClick = { category ->
                    selectedCategory =
                        category?.name ?: "All"
                }
            )
        }

        // ============================================================
        // RECOMMENDED
        // ============================================================

        item(
            key = "recommended_section"
        ) {

            RecommendedWithDealsSection(
                restaurant = RestaurantMockData.recommendedRestaurants,

                onRestaurantClick = { restaurant ->

                    onRestaurantClick(
                        restaurant.id
                    )
                }
            )
        }

        // ============================================================
        // DIVIDER
        // ============================================================

        item(
            key = "recommended_divider"
        ) {

            HomeDivider()
        }

        // ============================================================
        // NEARBY
        // ============================================================

        item(
            key = "nearby_section"
        ) {
            NearbyRestaurantSection(
                restaurants = RestaurantMockData.nearbyRestaurants,
                onRestaurantClick = { restaurant ->
                    onRestaurantClick(
                        restaurant.id
                    )
                }
            )
        }

        // ============================================================
        // DIVIDER
        // ============================================================

        item(
            key = "nearby_divider"
        ) {

            HomeDivider(
                verticalPadding = 10.dp
            )
        }

        // ============================================================
        // ALL RESTAURANTS HEADER
        // ============================================================

        item(
            key = "all_restaurant_header"
        ) {

            AllRestaurantHeader()
        }

        // ============================================================
        // ALL RESTAURANTS
        //
        // IMPORTANT:
        // These are individual LazyColumn items.
        // Do NOT put them inside Column/forEach.
        // ============================================================

        items(
            count = RestaurantMockData.nearbyRestaurants.size,

            key = { index ->
                "restaurant_${RestaurantMockData.nearbyRestaurants[index].id}"
            }
        ) { index ->

            val restaurant =
                RestaurantMockData.nearbyRestaurants[index]

            AllRestaurantCard(
                restaurant = restaurant,
                onClick = {
                    onRestaurantClick(
                        restaurant.id
                    )
                },
                onFavouriteClick = {}
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        // ============================================================
        // FOOTER
        // ============================================================

        item(
            key = "home_footer"
        ) {

            HomeFooterSection(
                modifier = Modifier.padding(
                    top = 16.dp
                )
            )
        }
    }

    // ================================================================
    // LOCATION BOTTOM SHEET
    // ================================================================

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
                // Save selected location
            }
        )
    }
}

@Composable
private fun HomeSearchAndCategoryHeader(
    selectedCategory: String,
    showElevation: Boolean,
    onSearchClick: () -> Unit,
    onCategoryClick: (FoodCategory?) -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = if (showElevation) {
            2.dp
        } else {
            0.dp
        }
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            // ========================================================
            // SEARCH
            // ========================================================

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
                    .clickable(
                        onClick = onSearchClick
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

            // ========================================================
            // CATEGORIES
            // ========================================================

            CategorySection(
                categories = foodCategories,
                selectedCategory = selectedCategory,
                onSeeAllClick = {},

                onCategoryClick = onCategoryClick
            )

            HomeDivider()
        }
    }
}

@Composable
private fun HomeDivider(
    verticalPadding: androidx.compose.ui.unit.Dp = 0.dp
) {

    HorizontalDivider(
        modifier = Modifier.padding(
            horizontal = 12.dp,
            vertical = verticalPadding
        ),
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@Composable
private fun AllRestaurantHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 16.dp
            )
    ) {

        Text(
            text = "${RestaurantMockData.nearbyRestaurants.size} RESTAURANTS SERVES TO YOU",
            style = MaterialTheme.typography.bodyMedium.copy(
                letterSpacing = 1.8.sp
            ),
            fontWeight = FontWeight.SemiBold,
            color = LightTextSecondary
        )
    }
}