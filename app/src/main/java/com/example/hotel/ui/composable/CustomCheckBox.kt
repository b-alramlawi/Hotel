package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.hotel.ui.theme.spacingXSmall
import com.example.hotel.ui.theme.textPrimaryColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomCheckbox(modifier: Modifier = Modifier, title: String, value: Boolean, onValueChange: (value: Boolean) -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacingXSmall)
    ) {
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                checked = value,
                onCheckedChange = onValueChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colors.primary,
                    uncheckedColor = MaterialTheme.colors.primary
                ),
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.button.copy(
                color = MaterialTheme.colors.textPrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}