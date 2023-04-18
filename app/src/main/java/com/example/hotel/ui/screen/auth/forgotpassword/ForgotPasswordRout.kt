package com.example.hotel.ui.screen.auth.forgotpassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_FORGOT_PASSWORD = "forgot_password"

fun NavController.navigateToForgotPassword() {
    navigate(ROUTE_FORGOT_PASSWORD)
}

fun NavGraphBuilder.forgotPasswordRoute(navController: NavController) {
    composable(
        route = ROUTE_FORGOT_PASSWORD,
    ) {
        ForgotPasswordScreen(navController = navController)
    }
}