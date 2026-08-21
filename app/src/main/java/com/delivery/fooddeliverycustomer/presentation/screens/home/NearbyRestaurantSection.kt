package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.delivery.fooddeliverycustomer.core.theme.LightTextSecondary
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant
import com.delivery.fooddeliverycustomer.presentation.widgets.RestaurantCard

@Composable
fun NearbyRestaurantSection(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit
) {

    if (restaurants.isEmpty()) {
        return
    }

    val columns = remember(restaurants) {
        restaurants.chunked(2)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp
            )
    ) {

        // ------------------------------------------------------------
        // HEADER
        // ------------------------------------------------------------

        Text(
            text = "NEARBY RESTAURANTS",
            modifier = Modifier.padding(
                horizontal = 12.dp
            ),
            style = MaterialTheme.typography.bodyMedium.copy(
                letterSpacing = 1.8.sp
            ),
            fontWeight = FontWeight.SemiBold,
            color = LightTextSecondary
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // ------------------------------------------------------------
        // RESTAURANTS
        // ------------------------------------------------------------

        LazyRow(
            modifier = Modifier.fillMaxWidth(),

            contentPadding = PaddingValues(
                horizontal = 12.dp
            ),

            horizontalArrangement = Arrangement.spacedBy(
                12.dp
            )
        ) {

            items(
                items = columns,
                key = { column ->
                    column.firstOrNull()?.id ?: column.hashCode()
                }
            ) { columnRestaurants ->

                Column(
                    modifier = Modifier.width(180.dp),

                    verticalArrangement = Arrangement.spacedBy(
                        10.dp
                    )
                ) {

                    columnRestaurants.forEach { restaurant ->

                        RestaurantCard(
                            restaurant = restaurant,

                            modifier = Modifier
                                .fillMaxWidth(),

                            onClick = {
                                onRestaurantClick(
                                    restaurant
                                )
                            },

                            onFavouriteClick = {}
                        )
                    }
                }
            }
        }
    }
}