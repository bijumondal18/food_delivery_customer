package com.delivery.fooddeliverycustomer.presentation.components.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.delivery.fooddeliverycustomer.presentation.components.buttons.AppTextButton

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)

        AppTextButton(
            text = "Retry",
            onClick = onRetry
        )
    }
}