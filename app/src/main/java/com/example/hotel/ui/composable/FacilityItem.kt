package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R
import com.example.hotel.domain.model.Facility
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun FacilityItem(facility: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        SubcomposeAsyncImage(
//            model = facility.image,
//            contentDescription = "details_picture",
//            contentScale = ContentScale.Crop,
//            loading = { CircularProgressIndicator() },
//        )
        Image(
            painter = painterResource(id = R.drawable.facility),
            contentDescription = "icon",
            modifier = Modifier.size(30.dp)
        )
        Text(
            text = facility,
            style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.textPrimaryColor)
        )
    }
}