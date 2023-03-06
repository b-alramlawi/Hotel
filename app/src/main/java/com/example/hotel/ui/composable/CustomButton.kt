package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.Green300
import com.example.hotel.ui.theme.Shapes

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    textColor: Color,
    disableColor: Color = Green300,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(58.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = disableColor
        ),
        shape = Shapes.large,
        enabled = enabled
    ) {
        Text(text = title, style = MaterialTheme.typography.body2.copy(color = textColor))
    }
}