package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.textFifthColor

@Composable
fun HotelItem() {
    Card(shape = RoundedCornerShape(36.dp), modifier = Modifier.aspectRatio(1.3f)) {
        Box() {
            Image(painter = painterResource(id = R.drawable.hotel), contentDescription = "hotel")
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black,
                            ), startY = 300f
                        )
                    )
                    .fillMaxSize()
            )
            Card(
                shape = Shapes.large,
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                elevation = 0.dp
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_bold),
                        contentDescription = "star",
                        tint = MaterialTheme.colors.textFifthColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "4.5",
                        style = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.textFifthColor))
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Emeralda De Hotel",
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
                )
                Text(
                    text = "Paris, France",
                    style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.textFifthColor, fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "29",
                            style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
                        )
                        Text(
                            text = "/ per night",
                            style = MaterialTheme.typography.body2.copy(MaterialTheme.colors.textFifthColor, fontWeight = FontWeight.Normal)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.bookmark_light),
                            contentDescription = "bookmark",
                            tint = MaterialTheme.colors.textFifthColor
                        )
                    }
                }
            }
        }
    }
}