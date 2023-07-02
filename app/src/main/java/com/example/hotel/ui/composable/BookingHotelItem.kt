package com.example.hotel.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R
import com.example.hotel.domain.model.Booking
import com.example.hotel.domain.model.BookingStatus
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.theme.*

@Composable
fun BookingHotelItem(modifier: Modifier = Modifier, hotel: com.example.hotel.data.remote.response.dto.home.BookingStatus) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.cardBackground
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row() {
                Card(
                    shape = Shapes.medium,
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
                    modifier = Modifier.height(90.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = hotel.name,
                        style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.textPrimaryColor)
                    )
//                    Text(
//                        text = hotel.location,
//                        style = MaterialTheme.typography.button.copy(
//                            MaterialTheme.colors.textSecondaryColor,
//                            fontWeight = FontWeight.Normal
//                        )
//                    )
                    CustomTag(
                        title = hotel.paymentStatus,
                        color = if (hotel.paymentStatus == BookingStatus.CANCELED) Red500 else MaterialTheme.colors.primary
                    )
                }
            }
//            if (hotel.paymentStatus == BookingStatus.ONGOING) {
//                Divider()
//                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
//                    CustomButton(
//                        modifier = Modifier.fillMaxWidth(0.5f).height(40.dp),
//                        title = stringResource(id = R.string.view_ticket),
//                        color = MaterialTheme.colors.primary,
//                        textColor = White,
//                        onClick = {}
//                    )
//                    CustomOutlinedButton(
//                        title = stringResource(id = R.string.cancel_booking),
//                        textColor = MaterialTheme.colors.primary,
//                        onClick = {}
//                    )
//                }
//            }
        }
    }
}