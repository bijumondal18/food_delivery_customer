package com.delivery.fooddeliverycustomer.presentation.components.fields


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    enabled: Boolean = true,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(label)
        },
        placeholder = {
            Text("Enter your password")
        },
        singleLine = true,
        enabled = enabled,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisible = !passwordVisible
                }
            ) {
                Icon(
                    imageVector = if (passwordVisible) {
                        Icons.Rounded.VisibilityOff
                    } else {
                        Icons.Rounded.Visibility
                    },
                    contentDescription = if (passwordVisible) {
                        "Hide password"
                    } else {
                        "Show password"
                    }
                )
            }
        },
        supportingText = {
            errorMessage?.let {
                Text(it)
            }
        }
    )
}