package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.Green300
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.White
import com.example.hotel.ui.theme.heightDefaultButton

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = MaterialTheme.colors.primary,
    textColor: Color = White,
    disableColor: Color = Green300,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(heightDefaultButton),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            disabledBackgroundColor = disableColor,
        ),
        shape = Shapes.large,
        enabled = enabled,
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.body2.copy(color = textColor))
    }
}