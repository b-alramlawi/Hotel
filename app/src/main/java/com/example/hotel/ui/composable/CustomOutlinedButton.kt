package com.example.hotel.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.Green300
import com.example.hotel.ui.theme.Shapes

@Composable
fun CustomOutlinedButton(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color,
    onClick: () -> Unit
){
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = onClick,
        shape = Shapes.large,
        border = BorderStroke(color = textColor, width = 1.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
        ),
    ) {
        Text(text = title, style = MaterialTheme.typography.body2.copy(color = textColor))
    }
}