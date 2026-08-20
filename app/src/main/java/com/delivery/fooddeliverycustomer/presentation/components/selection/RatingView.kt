package com.delivery.fooddeliverycustomer.presentation.components.selection


import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RatingView(
    rating: Double,
    totalRatings: Int? = null
) {
    Row {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "Rating"
        )

        Text(
            text = String.format("%.1f", rating)
        )

        totalRatings?.let {
            Text(
                text = " ($it)"
            )
        }
    }
}