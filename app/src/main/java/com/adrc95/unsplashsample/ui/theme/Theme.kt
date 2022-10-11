package com.adrc95.unsplashsample.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color(0xFF51bba8),
    primaryVariant = Color(0xFF85cfc2),
    secondary = Color(0xFFd14b63),
    secondaryVariant = Color(0xFFda6e82)
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF51bba8),
    primaryVariant = Color(0xFF85cfc2),
    secondary = Color(0xFFd14b63),
    secondaryVariant = Color(0xFFda6e82)

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun UnSplashSampleComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}