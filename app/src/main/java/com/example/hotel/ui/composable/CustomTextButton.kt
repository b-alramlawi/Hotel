package com.example.hotel.ui.composable

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextButton(title: String, onClick: () -> Unit){
    CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
        TextButton(onClick = onClick) {
            Text(
                text = title,
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.primary,
                )
            )
        }
    }
}