package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.CalculatorTheme

/**
 * Emphasis levels corresponding to the design specifications:
 * - HIGH: 0xFF4B5EFC (Operators: ÷, ×, -, +, =)
 * - MEDIUM: 0xFF4E505F (Functions: C, +/-, %)
 * - LOW: 0xFF2E2F38 (Numbers: 0-9, ., ⌫)
 */
enum class ButtonEmphasis {
    HIGH,
    MEDIUM,
    LOW
}

/**
 * Custom squircle keypad button with touch states and emphasis-based styling.
 */
@Composable
fun CalculatorButton(
    symbol: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    emphasis: ButtonEmphasis = ButtonEmphasis.LOW,
    backgroundColor: Color? = null,
    textColor: Color? = null
) {
    val resolvedBgColor = backgroundColor ?: when (emphasis) {
        ButtonEmphasis.HIGH -> CalculatorTheme.colors.highEmphasis
        ButtonEmphasis.MEDIUM -> CalculatorTheme.colors.mediumEmphasis
        ButtonEmphasis.LOW -> CalculatorTheme.colors.lowEmphasis
    }

    val resolvedTextColor = textColor ?: when (emphasis) {
        ButtonEmphasis.HIGH -> CalculatorTheme.colors.textOperator
        ButtonEmphasis.MEDIUM, ButtonEmphasis.LOW -> CalculatorTheme.colors.textPrimary
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(24.dp))
            .background(resolvedBgColor)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = symbol,
            color = resolvedTextColor,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
