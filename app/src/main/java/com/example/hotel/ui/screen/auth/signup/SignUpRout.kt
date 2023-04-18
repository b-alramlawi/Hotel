package com.example.hotel.ui.screen.auth.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SIGN_UP = "sign_up"

fun NavController.navigateToSignUp() {
    navigate(ROUTE_SIGN_UP)
}

fun NavGraphBuilder.signUpRoute(navController: NavController) {
    composable(route = ROUTE_SIGN_UP) {
        SignUpScreen(navController = navController)
    }
}