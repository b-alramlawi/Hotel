package com.example.hotel.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.composable.home.HotelInfo
import com.example.hotel.ui.composable.home.ImageWithShadow
import com.example.hotel.ui.composable.home.RoundedRating

@Composable
fun HotelItem(hotel: Hotel, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(36.dp),
        modifier = Modifier
            .width(250.dp)
            .height(350.dp)
            .clickable { onClick() }
    ) {
        Box {
            ImageWithShadow(image = hotel.image)
            RoundedRating(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp),
                rate = hotel.rate
            )
            HotelInfo(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .wrapContentHeight(),
                hotel = hotel
            )
        }
    }
}