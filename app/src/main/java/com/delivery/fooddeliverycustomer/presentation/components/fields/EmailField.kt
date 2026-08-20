package com.delivery.fooddeliverycustomer.presentation.components.fields


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text("Email")
        },
        placeholder = {
            Text("Enter your email")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email
        ),
        singleLine = true,
        enabled = enabled,
        isError = isError,
        supportingText = {
            errorMessage?.let {
                Text(it)
            }
        }
    )
}