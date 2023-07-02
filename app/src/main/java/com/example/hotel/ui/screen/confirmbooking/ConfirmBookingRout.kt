package com.example.hotel.ui.screen.confirmbooking

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hotel.ui.screen.auth.setupprofile.SetupProfileScreenArgs

const val ROUTE_CONFIRM_Booking = "confirm_booking"

fun NavController.navigateToConfirmBooking(hotelId: String, price: String) {
    navigate("$ROUTE_CONFIRM_Booking/$hotelId/$price")
}

fun NavGraphBuilder.confirmBookingRoute(navController: NavController) {
    composable(
        route = "$ROUTE_CONFIRM_Booking/{${ConfirmBookingScreenArgs.HOTEL_ID}}/{${ConfirmBookingScreenArgs.PRICE}}",
        arguments = listOf(
            navArgument(name = ConfirmBookingScreenArgs.HOTEL_ID) { NavType.StringType },
            navArgument(name = ConfirmBookingScreenArgs.PRICE) { NavType.StringType },
        )
    ) {
        ConfirmBookingScreen(navController = navController, arguments = it.arguments!!)
    }
}

class ConfirmBookingScreenArgs(savedStateHandle: SavedStateHandle) {
    val hotelId: String? = savedStateHandle[HOTEL_ID]
    val price: String? = savedStateHandle[PRICE]

    companion object {
        const val HOTEL_ID = "hotelId"
        const val PRICE = "price"
    }
}