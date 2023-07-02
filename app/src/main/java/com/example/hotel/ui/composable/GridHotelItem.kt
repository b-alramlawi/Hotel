package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R
import com.example.hotel.domain.model.Hotel
import com.example.hotel.domain.model.HotelDB
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.star
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor


@Composable
fun GridHotelItem(modifier: Modifier = Modifier, hotel: HotelDB) {
    Card(
        modifier = modifier.width(120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 7.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                shape = Shapes.medium,
                elevation = 0.dp,
                modifier = Modifier.fillMaxWidth().height(100.dp)
            ) {
                SubcomposeAsyncImage(
                    model = "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
                    contentDescription = "hotel",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.textPrimaryColor)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.6f),
                        text = hotel.location,
                        style = MaterialTheme.typography.button.copy(
                            MaterialTheme.colors.textSecondaryColor,
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
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
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
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
                    }
                    Spacer(modifier = Modifier.width(10.dp))
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
}