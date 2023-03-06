package com.example.hotel.ui.composable.bottom_navigation_bar

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Painter,
)