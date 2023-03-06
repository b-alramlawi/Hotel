package com.example.hotel.ui.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.hotel.ui.navigation.RootNavigationGraph
import com.example.hotel.ui.theme.HotelTheme

@Composable
fun HotelApp() {
    HotelTheme {
        val navController = rememberNavController()

        RootNavigationGraph(navController)
    }
}
