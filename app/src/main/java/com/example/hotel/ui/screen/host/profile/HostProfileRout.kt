package com.example.hotel.ui.screen.host.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOST_PROFILE = "host_profile"

fun NavController.navigateToHostProfile() {
    navigate(ROUTE_HOST_PROFILE)
}

fun NavGraphBuilder.hostProfileRoute(navController: NavController) {
    composable(route = ROUTE_HOST_PROFILE) {
        HostProfileScreen()
    }
}