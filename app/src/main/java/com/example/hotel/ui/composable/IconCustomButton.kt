package com.example.hotel.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.*

@Composable
fun IconCustomButton(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    icon: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(heightDefaultButton),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        shape = Shapes.medium,
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.secondaryVariant),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                Spacer(modifier = Modifier.width(spacingLarge))
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    tint = MaterialTheme.colors.textSecondaryColor
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.textSecondaryColor)
            )
        }
    }
}