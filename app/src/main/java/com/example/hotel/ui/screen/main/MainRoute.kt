package com.example.hotel.ui.screen.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

const val ROUTE_MAIN = "main"

fun NavController.navigateToMain() {
    navigate(ROUTE_MAIN)
}

fun NavGraphBuilder.mainRoute(navController: NavController) {
    composable(ROUTE_MAIN) {
        MainScreen(
            navController = rememberNavController(),
            rootNavController = navController
        )
    }
}