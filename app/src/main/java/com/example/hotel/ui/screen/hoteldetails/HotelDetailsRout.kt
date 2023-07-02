package com.example.hotel.ui.screen.hoteldetails

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hotel.ui.screen.auth.forgotpassword.ForgotPasswordScreenArgs

const val ROUTE_HOTEL_DETAILS = "hotel_details"

fun NavController.navigateToHotelDetails(id: String) {
    navigate("$ROUTE_HOTEL_DETAILS/$id")
}

fun NavGraphBuilder.hotelDetailsRoute(navController: NavController) {
    composable(
        route = "$ROUTE_HOTEL_DETAILS/{${ HotelDetailsScreenArgs.ID}}",
        arguments = listOf(
            navArgument(name = HotelDetailsScreenArgs.ID) { NavType.StringType },
        )
    ) {
        HotelDetailsScreen(navController = navController, arguments = it.arguments!!)
    }
}

class HotelDetailsScreenArgs(savedStateHandle: SavedStateHandle) {
    val id: String? = savedStateHandle[ID]

    companion object {
        const val ID = "id"
    }
}