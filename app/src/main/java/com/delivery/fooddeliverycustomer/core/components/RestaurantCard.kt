package com.delivery.fooddeliverycustomer.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.delivery.fooddeliverycustomer.data.model.Restaurant

@Composable
 fun RestaurantCard(
    restaurant: Restaurant,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Surface (
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        tonalElevation = 0.05.dp,
        border = BorderStroke(width = 0.5.dp, color = MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = modifier
                .clip(
                    RoundedCornerShape(14.dp)
                )
                .background(
                    MaterialTheme.colorScheme.surface
                )
                .clickable {
                    onClick()
                }
        ) {

            // --------------------------------------------------
            // Restaurant Image
            // --------------------------------------------------

            AsyncImage(
                model = restaurant.imageUrl,
                contentDescription = restaurant.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.25f)
                    .clip(
                        RoundedCornerShape(
                            topStart = 14.dp,
                            topEnd = 14.dp
                        )
                    )
            )

            // --------------------------------------------------
            // Restaurant Info
            // --------------------------------------------------

            Column(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
            ) {

                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = restaurant.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(5.dp)
                            )
                            .background(
                                MaterialTheme.colorScheme.primary
                            )
                            .padding(
                                horizontal = 5.dp,
                                vertical = 2.dp
                            )
                    ) {

                        Text(
                            text = "★ ${restaurant.rating}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}