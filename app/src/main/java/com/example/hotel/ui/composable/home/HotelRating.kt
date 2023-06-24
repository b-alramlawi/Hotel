package com.example.hotel.ui.composable.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.hotel.ui.theme.star
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun HotelRating(rate: Float, reviews: Int){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.star_bold),
            contentDescription = "star",
            tint = MaterialTheme.colors.star,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rate.toString(),
            style = MaterialTheme.typography.button.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.primary
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "(${reviews})" + stringResource(id = R.string.reviews),
            style = MaterialTheme.typography.caption.copy(
                MaterialTheme.colors.textSecondaryColor,
                fontWeight = FontWeight.Normal
            )
        )
    }
}