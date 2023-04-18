package com.example.hotel.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun SettingItem(icon: Int, title: String, textColor: Color = MaterialTheme.colors.textPrimaryColor, onClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Icon(painter = painterResource(id = icon), contentDescription = "icon", tint = textColor)
        Text(
            text = title,
            style = MaterialTheme.typography.body2.copy(color = textColor)
        )
    }
}