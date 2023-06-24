package com.example.hotel.ui.theme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val horizontalSpacing = 24.dp
val verticalSpacing = 16.dp

val spacingSmall: Dp = 8.dp
val spacingXSmall: Dp = 12.dp
val spacingMedium: Dp = 16.dp
val spacingXMedium: Dp = 20.dp
val spacingLarge: Dp = 24.dp
val spacingHuge: Dp = 32.dp

val sizeIndicator: Dp = 10.dp
val heightDefaultButton: Dp = 50.dp
val heightSmallButton: Dp = 30.dp
val heightInput: Dp = 55.dp

val roundedLarge: Dp = 24.dp

@Composable
fun topPaddingValue(): Dp{
    return WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
}

@Composable
fun bottomPaddingValue(): Dp{
    return WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
}



