package com.example.hotel.ui.screen.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "register"

fun NavController.navigateToRegister() {
    navigate(ROUTE)
}

fun NavGraphBuilder.registerRoute(navController: NavController) {
    composable(ROUTE) {
        RegisterScreen(navController)
    }
}