package com.example.hotel.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_PROFILE = "profile"

fun NavController.navigateToProfile() {
    navigate(ROUTE_PROFILE)
}

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(
        route = ROUTE_PROFILE,
    ) {
        ProfileScreen(navController = navController)
    }
}