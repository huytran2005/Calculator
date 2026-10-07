# Technical Specification & Implementation Plan

**Project:** Calculator for Android  
**Platform:** Android (Min SDK 24, Target SDK 36)  
**Framework:** Kotlin, Jetpack Compose, Material 3  
**Architecture:** MVVM / MVI (Unidirectional Data Flow)  

---

## 1. Context & Objectives

The goal of this project is to implement a calculator application based on the provided Figma specifications. The application must support:
- Dual-theme interface (Dark & Light) with an interactive toggle switch.
- Standard 4x5 scientific-free keypad layout.
- High-precision calculation avoiding IEEE 754 floating-point errors.
- Responsive layout scaling for both handheld devices and tablets/foldables.

---

## 2. Design Tokens & UI Specifications

### 2.1. Color Tokens

| Token | Dark Theme | Light Theme | Description |
|---|---|---|---|
| `colorBackground` | `#17171C` | `#F1F2F3` | Scaffolding and app canvas background |
| `colorSurfaceNumber` | `#2E2F3E` | `#FFFFFF` | Numeric keys (`0-9`), decimal (`.`), backspace (`⌫`) |
| `colorSurfaceFunction` | `#4E505F` | `#D2D3DA` | Top function keys (`C`, `+/-`, `%`) |
| `colorSurfaceOperator` | `#4B5EFC` | `#4B5EFC` | Math operators (`÷`, `×`, `-`, `+`, `=`) |
| `colorTextPrimary` | `#FFFFFF` | `#000000` | Primary result display and number key text |
| `colorTextSecondary` | `#747477` | `#8A8A8E` | Expression history display |
| `colorTextOperator` | `#FFFFFF` | `#FFFFFF` | Symbol color on operator keys |
| `colorToggleTrack` | `#2E2F3E` | `#D2D3DA` | Theme toggle background track |
| `colorToggleThumb` | `#4E505F` | `#FFFFFF` | Theme toggle thumb indicator |

### 2.2. Typography

- **Primary Result:** Sans-Serif (system default), Bold, 56sp - 72sp, auto-shrinking down to 32sp when input length exceeds view width.
- **Secondary Expression:** Sans-Serif, Regular / Medium, 24sp - 32sp.
- **Keypad Labels:** Sans-Serif, Medium / SemiBold, 28sp - 32sp.

### 2.3. Keypad Geometry & Sizing

- **Aspect Ratio / Shape:** Rounded rectangle with corner radius of `24.dp`.
- **Grid Layout:** 4 columns x 5 rows.
- **Spacing:** `12.dp` horizontal and vertical item spacing on mobile devices; scaled/constrained on tablets.
- **Tablet Strategy:** On viewports wider than `600.dp`, the calculator UI is centered with a constrained max width (`480.dp` to `560.dp`) to maintain ergonomic reachability.

---

## 3. Architecture & Data Flow

The project follows Unidirectional Data Flow (UDF):

```text
[Compose View]  ----( CalculatorAction )---->  [CalculatorViewModel]
      ^                                                 |
      |                                        ( State Update )
      |                                                 v
[UI Rendering]  <---( StateFlow<CalculatorState> )------+
```

### 3.1. State Definition (`CalculatorState`)

```kotlin
data class CalculatorState(
    val currentInput: String = "0",
    val expression: String = "",
    val isDarkMode: Boolean = true,
    val isResultCalculated: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)
```

### 3.2. Actions Definition (`CalculatorAction`)

```kotlin
sealed interface CalculatorAction {
    data class Number(val value: Int) : CalculatorAction
    object Decimal : CalculatorAction
    object Clear : CalculatorAction
    object Delete : CalculatorAction
    data class Operation(val operation: CalculatorOperation) : CalculatorAction
    object Calculate : CalculatorAction
    object ToggleSign : CalculatorAction
    object Percent : CalculatorAction
    object ToggleTheme : CalculatorAction
}
```

### 3.3. Arithmetic Engine (`CalculatorEvaluator`)

- **Precision:** Uses `java.math.BigDecimal` with a `MathContext` of 16 decimal places to prevent inaccuracies (e.g., `0.1 + 0.2 = 0.3`).
- **Formatting:** Formats output with commas for thousands separation and strips redundant trailing zeroes (e.g., `1,250.00` becomes `1,250`).
- **Error Handling:** 
  - Division by zero returns error state (`Cannot divide by zero`).
  - Max input length limited to 15 digits to preserve UI layout integrity.

---

## 4. Implementation Checklist

### Phase 1: Foundations & Design Tokens
- [ ] Define color constants in `ui/theme/Color.kt`.
- [ ] Implement dark/light theme switching in `ui/theme/Theme.kt`.
- [ ] Configure typography scales in `ui/theme/Type.kt`.

### Phase 2: Domain Engine & Unit Testing
- [ ] Define `CalculatorOperation` enum.
- [ ] Implement `CalculatorEvaluator` with `BigDecimal` arithmetic.
- [ ] Implement number formatting utility (thousands separator, decimals).
- [ ] Add unit test suite `CalculatorEvaluatorTest` covering:
  - Basic arithmetic (`+`, `-`, `×`, `÷`)
  - Floating-point precision
  - Divide-by-zero handling
  - Percentage and sign negation
  - Multi-step chained calculations

### Phase 3: Component Development
- [ ] `ThemeToggleSwitch`: Animated pill switch with sun/moon icons.
- [ ] `DisplayScreen`: Right-aligned dual-line display with dynamic text measurement.
- [ ] `CalculatorButton`: Custom squircle button with press feedback and distinct color variants.
- [ ] `KeypadGrid`: 4x5 grid layout mapping.

### Phase 4: State Management & Integration
- [ ] Implement `CalculatorViewModel` managing `CalculatorState` via `MutableStateFlow`.
- [ ] Assemble `CalculatorScreen`.
- [ ] Wire root in `MainActivity.kt` with edge-to-edge system bar integration.

### Phase 5: Tablet & Multi-Window Adaptation
- [ ] Implement adaptive constraints for screens wider than `600.dp`.
- [ ] Verify portrait, landscape, and multi-window resizing behavior.
