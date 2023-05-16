package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hotel.R
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun Footer(modifier: Modifier = Modifier, message: String, textButton: String, onClick: () -> Unit){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.button.copy(
                color = MaterialTheme.colors.textSecondaryColor,
                fontWeight = FontWeight.Normal
            )
        )
        CustomTextButton(title = textButton, onClick = onClick)
    }
}