package com.delivery.fooddeliverycustomer.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.delivery.fooddeliverycustomer.core.components.RecommendedRestaurantCard
import com.delivery.fooddeliverycustomer.core.theme.LightTextSecondary
import com.delivery.fooddeliverycustomer.domain.model.restaurant.Restaurant

@Composable
fun RecommendedWithDealsSection(
    restaurant: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp
            )
    ) {

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Recommended With Deals".uppercase(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    letterSpacing = 1.8.sp
                ),
                fontWeight = FontWeight.SemiBold,
                color = LightTextSecondary,
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // Horizontal list
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = restaurant,
                key = { it.id }
            ) { kitchen ->

                RecommendedRestaurantCard(
                    restaurant = kitchen,
                    onClick = {
                        onRestaurantClick(kitchen)
                    }
                )
            }
        }
    }
}