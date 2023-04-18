package com.example.hotel.ui.screen.booking

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_Booking = "booking"

fun NavController.navigateToBooking() {
    navigate(ROUTE_Booking)
}

fun NavGraphBuilder.bookingRoute(navController: NavController) {
    composable(
        route = ROUTE_Booking,
    ) {
        BookingScreen(navController = navController)
    }
}