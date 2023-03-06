package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R

@Composable
fun CustomTag(title: String, color: Color) {
    Card(
        backgroundColor = color.copy(alpha = 0.1f),
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            style = MaterialTheme.typography.button.copy(
                color = color,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}