package com.delivery.fooddeliverycustomer.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
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

private val AppDarkColorScheme = darkColorScheme(

    primary = Primary,
    onPrimary = OnPrimary,

    secondary = Secondary,
    onSecondary = OnSecondary,

    background = DarkBackground,
    onBackground = DarkForeground,

    surface = DarkSurface,
    onSurface = DarkForeground,

    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkForeground,

    outline = DarkBorder,

    error = Error,
    onError = OnError
)

val AppLightGradient = Brush.verticalGradient(
    colorStops = arrayOf(
        0.0f to Color(0xFFFD510E),
        0.20f to Color(0xFFFF5D24),
        0.40f to Color(0xFFFF7A45),
        0.65f to Color(0xFFFFC2AA),
        0.82f to Color(0xFFFFE8DF),
        1.0f to Color.White
    )
)

val AppDarkGradient = Brush.verticalGradient(
    colors = listOf(
        DarkSurface,
        DarkSurface,
        DarkBackground,
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
    val colorScheme = if (darkTheme) {
        AppDarkColorScheme
    } else {
        AppLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}