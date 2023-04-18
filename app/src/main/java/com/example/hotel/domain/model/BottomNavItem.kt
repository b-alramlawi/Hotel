package com.example.hotel.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: Painter,
    val iconSelected: Painter,
)