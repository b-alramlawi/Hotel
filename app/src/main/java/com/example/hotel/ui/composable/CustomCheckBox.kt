package com.example.hotel.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.Shapes

@Composable
fun CustomCheckbox(title: String, value: Boolean, onValueChange: (value: Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = value,
            onCheckedChange = onValueChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primary,
                uncheckedColor = MaterialTheme.colors.primary
            ),
        )
        Text(text = title, style = MaterialTheme.typography.button)
    }
}