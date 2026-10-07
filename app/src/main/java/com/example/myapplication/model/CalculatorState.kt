package com.example.myapplication.model

/**
 * Single source of truth for the Calculator screen UI state.
 */
data class CalculatorState(
    val displayValue: String = "0"
    // TODO: Add secondary expression history state
    // TODO: Add active operation state
    // TODO: Add theme mode flag (isDarkMode)
    // TODO: Add error handling state (errorMessage)
)
