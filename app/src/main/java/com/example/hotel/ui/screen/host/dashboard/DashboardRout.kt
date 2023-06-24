package com.example.hotel.ui.screen.host.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

const val ROUTE_DASHBOARD = "dashboard"

fun NavController.navigateToDashboard() {
    navigate(ROUTE_DASHBOARD)
}

fun NavGraphBuilder.dashboardRoute(navController: NavController) {
    composable(route = ROUTE_DASHBOARD) {
        DashboardScreen(
            navController = rememberNavController(),
            rootNavController = navController
        )
    }
}