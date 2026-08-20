package com.delivery.fooddeliverycustomer.presentation.components.buttons


import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppTextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}