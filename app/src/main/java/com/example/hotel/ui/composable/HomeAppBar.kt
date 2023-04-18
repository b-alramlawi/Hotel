package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun HomeAppBar(title: String, actions: @Composable () -> Unit) {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "logo", modifier = Modifier.size(30.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        actions = {
             Row {
                 actions()
             }
        },
        elevation = 0.dp
    )
}