package com.example.hotel.ui.composable.bottom_navigation_bar

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ComposableNavigationIcon(item: BottomNavItem) {
    Icon(
        painter = item.icon,
        contentDescription = item.name,
        modifier = Modifier.size(24.dp),
    )
}