package com.delivery.fooddeliverycustomer.presentation.components.cards


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.core.theme.LightBackground
import com.delivery.fooddeliverycustomer.core.theme.LightBorder

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.1.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = LightBorder
        ),
        colors = CardDefaults.cardColors(
            containerColor = LightBackground
        )
    ) {
        content()
    }
}