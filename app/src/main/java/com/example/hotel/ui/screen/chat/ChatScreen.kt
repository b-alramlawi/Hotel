package com.example.hotel.ui.screen.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatScreen(
    navController: NavController,
) {
    ChatContent(
        navController = navController,
    )
}

@Composable
private fun ChatContent(
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Chat", style = TextStyle(
                color = MaterialTheme.colors.error,
                fontSize = 40.sp
            )
        )
    }
}