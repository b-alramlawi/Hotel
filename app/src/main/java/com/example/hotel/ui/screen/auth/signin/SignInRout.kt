package com.example.hotel.ui.screen.auth.signin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SIGN_IN = "sign_in"

fun NavController.navigateToSignIn() {
    navigate(ROUTE_SIGN_IN)
}

fun NavGraphBuilder.signInRoute(navController: NavController) {
    composable(route = ROUTE_SIGN_IN) {
        SignInScreen(navController = navController)
    }
}