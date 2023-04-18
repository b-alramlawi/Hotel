package com.example.hotel.ui.composable.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hotel.R
import com.example.hotel.ui.theme.spacingXMedium
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun DividerWithText() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacingXMedium)
    ) {
        Divider(
            modifier = Modifier.fillMaxWidth(0.28f),
            color = MaterialTheme.colors.secondaryVariant
        )
        Text(
            text = stringResource(id = R.string.or_continue_with),
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.textSecondaryColor,
                fontWeight = FontWeight.Normal
            )
        )
        Divider(
            modifier = Modifier.fillMaxWidth(1f),
            color = MaterialTheme.colors.secondaryVariant
        )
    }
}