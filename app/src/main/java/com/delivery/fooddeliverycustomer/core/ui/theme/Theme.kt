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
    colors = listOf(
        GradientStart,
        GradientStart,
        GradientCenter,
        GradientCenter,
        GradientEnd,
        GradientEnd,
        Color.White

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