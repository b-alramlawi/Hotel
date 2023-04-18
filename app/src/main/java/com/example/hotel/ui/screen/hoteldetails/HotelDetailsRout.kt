package com.example.hotel.ui.screen.hoteldetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOTEL_DETAILS = "hotel_details"

fun NavController.navigateToHotelDetails() {
    navigate(ROUTE_HOTEL_DETAILS)
}

fun NavGraphBuilder.hotelDetailsRoute(navController: NavController) {
    composable(
        route = ROUTE_HOTEL_DETAILS,
    ) {
        HotelDetailsScreen(navController = navController)
    }
}