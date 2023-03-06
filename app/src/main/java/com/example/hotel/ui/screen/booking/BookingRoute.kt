package com.example.hotel.ui.screen.booking

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hotel.ui.navigation.MainRoute

private const val ROUTE = MainRoute.Booking
fun NavController.navigateToSearchScreen() {
    popBackStack()
}

fun NavGraphBuilder.bookingRoute(navController: NavController) {
    composable(ROUTE) { BookingScreen(navController) }
}
