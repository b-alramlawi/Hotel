package com.example.hotel.ui.composable.confirmhotel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun BookingDetails(title:String, value: String) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Check in",
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.textSecondaryColor,
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textPrimaryColor)
        )
    }
}