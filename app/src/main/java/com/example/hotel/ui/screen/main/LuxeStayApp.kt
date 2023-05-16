package com.example.hotel.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.hotel.ui.navigation.RootNavigationGraph
import com.example.hotel.ui.theme.HotelTheme

@Composable
fun LuxeStayApp() {
    HotelTheme {
        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        )
        RootNavigationGraph(navController = navController)
    }
}