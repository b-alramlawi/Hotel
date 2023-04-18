package com.example.hotel.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotel.domain.model.Gender
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun GenderBottomSheet(
    value: String,
    genders: ArrayList<Gender>,
    onSelectedChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.background)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            items(genders.size) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().clickable { onSelectedChange(genders[it].title) },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = genders[it].title,
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
                    RadioButton(
                        selected = value == genders[it].title,
                        onClick = { onSelectedChange(genders[it].title) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colors.primary,
                            unselectedColor = MaterialTheme.colors.primary
                        )
                    )
                }
            }
        }
    }
}

