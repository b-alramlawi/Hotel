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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.theme.Blake
import com.example.hotel.ui.theme.Blake800
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.textFifthColor

@Composable
fun HotelItem(hotel: Hotel, onClick: () -> Unit) {
    Card(shape = RoundedCornerShape(36.dp), modifier = Modifier
        .width(250.dp)
        .height(350.dp)
        .clickable { onClick() }) {
        Box {
            Image(
                painter = painterResource(id = hotel.image),
                contentDescription = "hotel",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Blake800,
                            ), startY = 500f
                        )
                    )
                    .fillMaxSize()
            )
            Card(
                shape = Shapes.large,
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(20.dp),
                elevation = 0.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_bold),
                        contentDescription = "star",
                        tint = MaterialTheme.colors.textFifthColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = hotel.rate.toString(),
                        style = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.textFifthColor
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
                )
                Text(
                    text = hotel.location,
                    style = MaterialTheme.typography.body2.copy(
                        MaterialTheme.colors.textFifthColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = hotel.price.toString(),
                            style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
                        )
                        Text(
                            text = "/ per night",
                            style = MaterialTheme.typography.body2.copy(
                                MaterialTheme.colors.textFifthColor,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.bookmark_light),
                        contentDescription = "bookmark",
                        tint = MaterialTheme.colors.textFifthColor,
                        modifier = Modifier.clickable {  }
                    )
                }
            }
        }
    }
}