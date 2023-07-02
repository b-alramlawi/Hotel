package com.example.hotel.ui.composable.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.domain.model.Hotel
import com.example.hotel.ui.theme.textFifthColor

@Composable
fun HotelInfo(modifier: Modifier = Modifier, hotel: com.example.hotel.data.remote.response.dto.home.Hotel){
    Column(
        modifier = modifier
    ) {
        Text(
            text = hotel.name,
            style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
        )
        Text(
            text = hotel.Location.locationName,
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
                    text = stringResource(id = R.string.night),
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