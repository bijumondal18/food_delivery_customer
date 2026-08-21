package com.delivery.fooddeliverycustomer.presentation.components.selection

import com.delivery.fooddeliverycustomer.core.theme.LightTextSecondary
import com.delivery.fooddeliverycustomer.core.theme.Primary


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.delivery.fooddeliverycustomer.core.theme.LightBackground

enum class FoodPreference {
    VEG,
    NON_VEG
}

@Composable
fun VegNonVegSwitch(
    selected: FoodPreference,
    onSelectionChanged: (FoodPreference) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(LightBackground)
            .padding(4.dp)
    ) {
        PreferenceItem(
            text = "Veg",
            selected = selected == FoodPreference.VEG,
            onClick = {
                onSelectionChanged(FoodPreference.VEG)
            }
        )

        PreferenceItem(
            text = "Non-Veg",
            selected = selected == FoodPreference.NON_VEG,
            onClick = {
                onSelectionChanged(FoodPreference.NON_VEG)
            }
        )
    }
}

@Composable
private fun PreferenceItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable(onClick = onClick)
            .background(
                if (selected) {
                    Primary
                } else {
                    LightTextSecondary
                }
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    )
}