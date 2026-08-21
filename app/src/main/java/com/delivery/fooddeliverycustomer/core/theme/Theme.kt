package com.delivery.fooddeliverycustomer.core.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

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

    // App is only in Light Mode - Configuration of StatusBar Icons
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}