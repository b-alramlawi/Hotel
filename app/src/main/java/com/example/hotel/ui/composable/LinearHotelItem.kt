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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.star
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun LinearHotelItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Row(modifier = Modifier.padding(15.dp)) {
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.25f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hotel),
                    contentDescription = "hotel",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Emeralda De Hotel",
                    style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.textPrimaryColor)
                )
                Text(
                    text = "Paris, France",
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
                        text = "4.5",
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
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$29",
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.primary)
                )
                Text(
                    text = "/ night",
                    style = MaterialTheme.typography.overline.copy(
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