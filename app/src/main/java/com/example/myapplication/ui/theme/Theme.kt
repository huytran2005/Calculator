package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Dedicated color tokens container for the Calculator design system.
 */
@Immutable
data class CalculatorColors(
    val background: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val highEmphasis: Color,    // 0xFF4B5EFC - Math operators
    val mediumEmphasis: Color,  // 0xFF4E505F - Function keys (C, +/-, %)
    val lowEmphasis: Color,     // 0xFF2E2F38 - Number keys (0-9, ., ⌫)
    val textOperator: Color = Color(0xFFFFFFFF),
    val toggleTrack: Color,
    val toggleThumb: Color
)

val DarkCalculatorColors = CalculatorColors(
    background = ColorBackground,
    textPrimary = ColorText,
    textSecondary = ColorTextSecondaryDark,
    highEmphasis = ColorHighEmphasis,
    mediumEmphasis = ColorMediumEmphasis,
    lowEmphasis = ColorLowEmphasis,
    textOperator = ColorTextOperator,
    toggleTrack = ColorLowEmphasis,
    toggleThumb = ColorMediumEmphasis
)

val LightCalculatorColors = CalculatorColors(
    background = LightColorBackground,
    textPrimary = LightColorTextPrimary,
    textSecondary = LightColorTextSecondary,
    highEmphasis = LightColorHighEmphasis,
    mediumEmphasis = LightColorMediumEmphasis,
    lowEmphasis = LightColorLowEmphasis,
    textOperator = ColorTextOperator,
    toggleTrack = LightColorMediumEmphasis,
    toggleThumb = Color(0xFFFFFFFF)
)

val LocalCalculatorColors = staticCompositionLocalOf { DarkCalculatorColors }

object CalculatorTheme {
    val colors: CalculatorColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCalculatorColors.current
}

private val DarkColorScheme = darkColorScheme(
    background = ColorBackground,
    surface = ColorLowEmphasis,
    primary = ColorHighEmphasis,
    secondary = ColorMediumEmphasis,
    onBackground = ColorText,
    onSurface = ColorText,
    onPrimary = ColorTextOperator
)

private val LightColorScheme = lightColorScheme(
    background = LightColorBackground,
    surface = LightColorLowEmphasis,
    primary = LightColorHighEmphasis,
    secondary = LightColorMediumEmphasis,
    onBackground = LightColorTextPrimary,
    onSurface = LightColorTextPrimary,
    onPrimary = ColorTextOperator
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val calculatorColors = if (darkTheme) DarkCalculatorColors else LightCalculatorColors
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(LocalCalculatorColors provides calculatorColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}