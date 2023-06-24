package com.example.hotel.ui.screen.host.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOST_HOME = "host_home"

fun NavController.navigateToHostHome() {
    navigate(ROUTE_HOST_HOME)
}

fun NavGraphBuilder.hostHomeRoute(navController: NavController) {
    composable(route = ROUTE_HOST_HOME) {
        HostHomeScreen()
    }
}