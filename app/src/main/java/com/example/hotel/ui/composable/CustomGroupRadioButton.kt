package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CustomGroupRadioButton(list: ArrayList<String>, value: String, onClick: (value: String) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            CustomRadioButton(title = it, value = value == it) {
                onClick(it)
            }
        }
    }
}