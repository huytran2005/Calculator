package com.example.myapplication.ui.theme

import androidx.compose.ui.graphics.Color

// ==========================================
// User Specified Palette (Dark Theme Tokens)
// ==========================================
val ColorBackground = Color(0xFF17171C)      // Background color
val ColorText = Color(0xFFFFFFFF)            // Text color (Primary)
val ColorHighEmphasis = Color(0xFF4B5EFC)    // High Emphasis: Operators (÷, ×, -, +, =)
val ColorMediumEmphasis = Color(0xFF4E505F)  // Medium Emphasis: Functions (C, +/-, %)
val ColorLowEmphasis = Color(0xFF2E2F38)     // Low Emphasis: Numbers (0-9), '.', '⌫'

// Supporting colors
val ColorTextSecondaryDark = Color(0xFF747477)
val ColorTextOperator = Color(0xFFFFFFFF)

// ==========================================
// Light Theme Counterparts (from Figma Specs)
// ==========================================
val LightColorBackground = Color(0xFFF1F2F3)
val LightColorTextPrimary = Color(0xFF000000)
val LightColorTextSecondary = Color(0xFF8A8A8E)
val LightColorHighEmphasis = Color(0xFF4B5EFC)
val LightColorMediumEmphasis = Color(0xFFD2D3DA)
val LightColorLowEmphasis = Color(0xFFFFFFFF)