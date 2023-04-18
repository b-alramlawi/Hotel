package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hotel.R
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun RecentlySearchItem(search: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = search,
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textSecondaryColor, fontWeight = FontWeight.Normal)
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.close_square_light),
                contentDescription = "delete",
                tint = MaterialTheme.colors.textSecondaryColor
            )
        }
    }
}