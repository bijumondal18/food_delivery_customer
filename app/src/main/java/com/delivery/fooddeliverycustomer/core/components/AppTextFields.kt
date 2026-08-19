package com.delivery.fooddeliverycustomer.core.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {

    val hasError = !errorMessage.isNullOrBlank()


    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        },
        label = {
            Text("Email")
        },
        placeholder = {
            Text("Enter your email")
        },
        supportingText = {
            if (hasError) {
                Text(
                    text = errorMessage.orEmpty(),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {

    val hasError = !errorMessage.isNullOrBlank()


    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        label = {
            Text("Password")
        },
        placeholder = {
            Text("Enter your password")
        },
        supportingText = {
            if (hasError) {
                Text(
                    text = errorMessage.orEmpty(),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}