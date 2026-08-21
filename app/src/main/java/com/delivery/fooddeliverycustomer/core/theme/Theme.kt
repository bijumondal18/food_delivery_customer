package com.delivery.fooddeliverycustomer.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val AppLightColorScheme = lightColorScheme(

    primary = Primary,
    onPrimary = OnPrimary,

    secondary = Secondary,
    onSecondary = OnSecondary,

    background = LightBackground,
    onBackground = LightForeground,

    surface = LightSurface,
    onSurface = LightForeground,

    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightForeground,

    outline = LightBorder,

    error = Error,
    onError = OnError
)

val AppLightGradient = Brush.verticalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFFFD510E),
        1.0f to Color.White,
    )
)

val AppHomeGradient = Brush.linearGradient(
    colors = listOf(
        GradientStart,
        GradientCenter,
        Color.White
    )
)


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = AppLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}