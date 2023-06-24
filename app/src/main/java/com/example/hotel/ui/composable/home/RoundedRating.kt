package com.example.hotel.ui.composable.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.textFifthColor

@Composable
fun RoundedRating(modifier: Modifier = Modifier, rate: Float){
    Card(
        shape = Shapes.large,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier,
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
                text = rate.toString(),
                style = MaterialTheme.typography.button.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.textFifthColor
                )
            )
        }
    }
}