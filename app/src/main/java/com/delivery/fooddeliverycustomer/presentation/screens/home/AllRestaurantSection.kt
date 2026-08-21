package com.delivery.fooddeliverycustomer.presentation.screens.home


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.core.components.AllRestaurantCard
import com.delivery.fooddeliverycustomer.core.components.RestaurantCard
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant

@Composable
fun AllRestaurantSection(
    restaurants: List<Restaurant>,
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "200 Restaurants Delivering To You".uppercase(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            restaurants
                .chunked(1)
                .forEach { rowRestaurants ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        rowRestaurants.forEach { restaurant ->

                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                AllRestaurantCard(
                                    restaurant = restaurant,
                                    onClick = {
                                        onRestaurantClick(restaurant)
                                    },
                                    onFavouriteClick = {}
                                )
                            }
                        }
                    }
                }
        }
    }
}