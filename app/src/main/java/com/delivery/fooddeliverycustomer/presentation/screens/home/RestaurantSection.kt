package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
//                horizontal = 16.dp,
                vertical = 12.dp
            )
    ) {

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Nearby Restaurants",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
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


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                horizontal = 12.dp
            )
        ) {

            items(
                items = restaurants.chunked(2)
            ) { columnRestaurants ->

                Column(
                    modifier = Modifier.fillMaxWidth(fraction = 0.3f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    columnRestaurants.forEach { restaurant ->

                        RestaurantCard(
                            restaurant = restaurant,
                            onClick = {
                                onRestaurantClick(restaurant)
                            },
                            onFavouriteClick = {}
                        )
                    }

                    // Keep the last column balanced
                    if (columnRestaurants.size == 1) {
                        Spacer(
                            modifier = Modifier.height(1.dp)
                        )
                    }
                }
            }
        }
    }
}