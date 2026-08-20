package com.delivery.fooddeliverycustomer.presentation.components.commons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.delivery.fooddeliverycustomer.presentation.components.buttons.AppTextButton

@Composable
fun EmptyView(
    message: String,
    actionText: String? = null,
    onAction: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)

        if (actionText != null && onAction != null) {
            AppTextButton(
                text = actionText,
                onClick = onAction
            )
        }
    }
}