package com.example.hotel.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hotel.ui.theme.sizeIndicator
import com.example.hotel.ui.theme.spacingSmall

@Composable
fun Indicator(size: Int, currentPage: Int, modifier: Modifier = Modifier) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(spacingSmall), modifier = modifier) {
        items(size) { page ->
            Box(
                modifier = Modifier
                    .size(sizeIndicator)
                    .background(
                        shape = CircleShape,
                        color = if (currentPage == page) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
                    )
            )
        }
    }
}