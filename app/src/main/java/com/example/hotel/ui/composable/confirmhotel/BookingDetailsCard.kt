package com.example.hotel.ui.composable.confirmhotel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotel.domain.model.BookingDetails
import com.example.hotel.ui.theme.cardBackground
import com.example.hotel.ui.theme.horizontalSpacing

@Composable
fun BookingDetailsCard(list: List<BookingDetails>){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalSpacing),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.cardBackground
    ) {
        LazyColumn(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(list){
                com.example.hotel.ui.composable.confirmhotel.BookingDetails(title = it.title, value = it.value)
            }
        }
    }
}