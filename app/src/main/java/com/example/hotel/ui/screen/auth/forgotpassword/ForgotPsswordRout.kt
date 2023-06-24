package com.example.hotel.ui.screen.auth.forgotpassword

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hotel.ui.screen.auth.setupprofile.ROUTE_SETUP_PROFILE
import com.example.hotel.ui.screen.auth.setupprofile.SetupProfileScreenArgs

const val ROUTE_FORGOT_PASSWORD = "forgot_password"

fun NavController.navigateToForgotPassword(email: String) {
    navigate("$ROUTE_FORGOT_PASSWORD/$email")
}

fun NavGraphBuilder.forgotPasswordRoute(navController: NavController) {
    composable(
        route = "$ROUTE_FORGOT_PASSWORD/{${ForgotPasswordScreenArgs.EMAIL}}",
        arguments = listOf(
            navArgument(name = ForgotPasswordScreenArgs.EMAIL) { NavType.StringType },
        )
    ) {
        ForgotPasswordScreen(navController = navController, arguments = it.arguments!!)
    }
}

class ForgotPasswordScreenArgs(savedStateHandle: SavedStateHandle) {
    val email: String? = savedStateHandle[EMAIL]

    companion object {
        const val EMAIL = "email"
    }
}