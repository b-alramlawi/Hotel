package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.Shapes

@Composable
fun ProfilePicture() {
    Box{
        Card(
            modifier = Modifier.size(120.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = "profilePicture"
            )
        }
        Card(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.BottomEnd)
                .clickable {  },
            shape = RoundedCornerShape(7.dp),
            backgroundColor = MaterialTheme.colors.primary,
        ){
            Icon(modifier = Modifier.padding(3.dp), painter = painterResource(id = R.drawable.edit_bold), contentDescription = "edit", tint = MaterialTheme.colors.background)
        }
    }
}