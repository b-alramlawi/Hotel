package com.example.hotel.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_HOME = "home"

fun NavController.navigateToHome() {
    navigate(ROUTE_HOME)
}

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(
        route = ROUTE_HOME,
    ) {
        HomeScreen(navController = navController)
    }
}