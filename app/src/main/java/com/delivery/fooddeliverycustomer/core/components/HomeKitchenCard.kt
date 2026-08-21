package com.delivery.fooddeliverycustomer.core.components


import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.domain.model.HomeKitchen
import com.delivery.fooddeliverycustomer.domain.model.homeKitchens
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun HomeKitchenCard(
    kitchen: com.delivery.fooddeliverycustomer.domain.model.HomeKitchen,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(150.dp)
            .clickable {
                onClick()
            }
    ) {

        AsyncImage(
            model = kitchen.imageUrl,
            contentDescription = kitchen.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(
                    RoundedCornerShape(14.dp)
                )
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = kitchen.name,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = kitchen.distance,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = " • ",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = kitchen.deliveryTime,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}