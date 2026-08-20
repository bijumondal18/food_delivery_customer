package com.delivery.fooddeliverycustomer.presentation.components.selection


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.R

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChanged: (Int) -> Unit,
    minQuantity: Int = 1,
    maxQuantity: Int = 99
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                if (quantity > minQuantity) {
                    onQuantityChanged(quantity - 1)
                }
            },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.close_24px), // TODO: should be minus icon
                contentDescription = "Decrease quantity"
            )
        }

        Text(
            text = quantity.toString()
        )

        IconButton(
            onClick = {
                if (quantity < maxQuantity) {
                    onQuantityChanged(quantity + 1)
                }
            },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Increase quantity"
            )
        }
    }
}