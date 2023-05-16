package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.composable.home.HotelRating
import com.example.hotel.ui.theme.*

@Composable
fun LinearHotelItem(modifier: Modifier = Modifier, hotel: Hotel) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.cardBackground
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = 0.dp,
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
            ) {
                Image(
                    painter = painterResource(id = hotel.image),
                    contentDescription = "hotel",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.textPrimaryColor)
                )
                Text(
                    text = hotel.location,
                    style = MaterialTheme.typography.button.copy(
                        MaterialTheme.colors.textSecondaryColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                HotelRating(rate = hotel.rate, reviews = hotel.reviews)
            }
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = hotel.price.toString() + "$",
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.primary)
                )
                Text(
                    text = stringResource(id = R.string.night),
                    style = MaterialTheme.typography.caption.copy(
                        MaterialTheme.colors.textSecondaryColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                Icon(
                    modifier = Modifier.clickable {  },
                    painter = painterResource(id = R.drawable.bookmark_light),
                    contentDescription = "bookmark",
                    tint = MaterialTheme.colors.textPrimaryColor
                )
            }
        }
    }
}