package com.example.hotel.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun CustomChip(
    selected: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    onSelectedChange: () -> Unit
) {
    Surface(
        color = if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.background,
        contentColor = if(selected) MaterialTheme.colors.background else MaterialTheme.colors.primary,
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = if(selected) Color.Transparent else MaterialTheme.colors.primary
        ),
        modifier = modifier.clickable { onSelectedChange() }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
        )
    }
}
