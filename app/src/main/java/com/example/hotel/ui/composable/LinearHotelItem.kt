package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.composable.home.HotelRating
import com.example.hotel.ui.theme.*

@Composable
fun LinearHotelItem(
    modifier: Modifier = Modifier,
    hotel: com.example.hotel.data.remote.response.dto.home.Hotel,
    onClick: () -> Unit,
    onBookMarkClick: () -> Unit,
//    isSaved: Boolean
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 5.dp)
            .clickable { onClick() },
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
                SubcomposeAsyncImage(
                    model = "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
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
                    text = hotel.Location.locationName,
                    style = MaterialTheme.typography.button.copy(
                        MaterialTheme.colors.textSecondaryColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                HotelRating(rate = hotel.rate.toFloat(), reviews = 3051)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
                    modifier = Modifier.clickable { onBookMarkClick();},
                    painter = painterResource(id = R.drawable.bookmark_light),
                    contentDescription = "bookmark",
                    tint = MaterialTheme.colors.textPrimaryColor
                )
            }
        }
    }
}