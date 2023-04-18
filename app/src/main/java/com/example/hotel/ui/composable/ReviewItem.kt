package com.example.hotel.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R
import com.example.hotel.domain.model.Review
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun ReviewItem(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 7.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier.size(50.dp),
                    shape = CircleShape,
                    elevation = 0.dp
                ) {
                    SubcomposeAsyncImage(
                        model = review.image,
                        contentDescription = "profile_picture",
                        contentScale = ContentScale.Crop,
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
                Column() {
                    Text(
                        text = review.name,
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
                    Text(
                        text = review.data,
                        style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.textSecondaryColor)
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Card(
                        shape = Shapes.large,
                        backgroundColor = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(20.dp),
                        elevation = 0.dp,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(
                                vertical = 5.dp,
                                horizontal = 10.dp
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.star_bold),
                                contentDescription = "star",
                                tint = MaterialTheme.colors.textFifthColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = review.rate.toString(),
                                style = MaterialTheme.typography.button.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colors.textFifthColor
                                )
                            )
                        }
                    }
                }
            }
            Text(
                text = review.details,
                style = MaterialTheme.typography.button.copy(
                    color = MaterialTheme.colors.textPrimaryColor,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}