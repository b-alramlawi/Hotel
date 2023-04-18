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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.theme.*

@Composable
fun LinearHotelItem(modifier: Modifier = Modifier, hotel: Hotel) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 7.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Card(
                shape = Shapes.medium,
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
                modifier = Modifier.height(90.dp),
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
                // Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.star_bold),
                        contentDescription = "star",
                        tint = MaterialTheme.colors.star,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = hotel.rate.toString(),
                        style = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.primary
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "(4,378 reviews)",
                        style = MaterialTheme.typography.caption.copy(
                            MaterialTheme.colors.textSecondaryColor,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().height(90.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = hotel.price.toString() + "$",
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.primary)
                )
                Text(
                    text = "/ night",
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