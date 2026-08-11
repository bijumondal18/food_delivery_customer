package com.delivery.fooddeliverycustomer.presentation.home

import androidx.compose.foundation.background
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
import com.delivery.fooddeliverycustomer.presentation.auth.login.LoginBottomSheet
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.R
import com.delivery.fooddeliverycustomer.core.components.FoodCategoryItem
import com.delivery.fooddeliverycustomer.data.model.FoodCategory


private val foodCategories = listOf(
    FoodCategory(
        "Pizza",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "Burger",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "Biryani",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "Chinese",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "Desserts",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "Drinks",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "South Indian",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    ),
    FoodCategory(
        "Fast Food",
        "https://png.pngtree.com/png-vector/20241211/ourmid/pngtree-authentic-italian-pizza-with-cheese-and-fresh-vegetable-toppings-png-image_14714611.png"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    showLoginSheet: Boolean,
    onDismissLoginSheet: () -> Unit,
    onGoogleLogin: () -> Unit,
    onPhoneLogin: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadLocation()
    }

    Scaffold() { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {

            // Compact App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Location

                Icon(
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp),
                    painter = painterResource(
                        R.drawable.location_on_24px
                    ),
                    contentDescription = "Location",
                    tint = MaterialTheme.colorScheme.primary
                )


                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = state.location?.address
                            ?: "Fetching location...",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1
                    )
                }

                // Notification
                IconButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onClick = {
                        // TODO: Navigate to notification screen
                    }
                ) {

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.10f
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            painter = painterResource(
                                R.drawable.notifications_24px
                            ),
                            contentDescription = "Notification",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                // Search bar
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .height(56.dp),
                    placeholder = {
                        Text("Search food, restaurants...")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(
                                R.drawable.search_24px
                            ),
                            contentDescription = "Search"
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // Categories title
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = "Categories",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(
                        items = foodCategories,
                        key = { it.name }
                    ) { category ->
                        FoodCategoryItem(
                            category = category,
                            onClick = {
                                // TODO: Handle category click
                            }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                // Top Rated Food title
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = "Top Rated Food",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }
        }

    }

    if (showLoginSheet) {

        LoginBottomSheet(

            onDismiss = {
                onDismissLoginSheet()
            },

            onGoogleLogin = {
                onGoogleLogin()
            },

            onPhoneLogin = {
                onPhoneLogin()
            }
        )
    }
}