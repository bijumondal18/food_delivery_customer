package com.delivery.fooddeliverycustomer.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.core.components.RestaurantCard
import com.delivery.fooddeliverycustomer.data.model.Restaurant

@Composable
fun RestaurantSection(
    restaurants: List<Restaurant>,
    onSeeAllClick: () -> Unit,
    onRestaurantClick: (Restaurant) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            )
    ) {

        // --------------------------------------------------
        // Section Header
        // --------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Nearby Restaurants",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "See All",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        onSeeAllClick()
                    }
                    .padding(
                        horizontal = 8.dp,
                        vertical = 6.dp
                    )
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // --------------------------------------------------
        // 3 Column Grid
        // --------------------------------------------------

        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            restaurants
                .chunked(3)
                .forEach { rowRestaurants ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        rowRestaurants.forEach { restaurant ->

                            RestaurantCard(
                                restaurant = restaurant,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onRestaurantClick(restaurant)
                                }
                            )
                        }

                        // Fill remaining space if last row
                        repeat(
                            3 - rowRestaurants.size
                        ) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
        }
    }
}