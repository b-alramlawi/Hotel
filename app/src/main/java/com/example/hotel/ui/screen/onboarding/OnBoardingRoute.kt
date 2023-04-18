package com.example.hotel.ui.screen.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_ON_BOARDING = "on_boarding"

fun NavController.navigateToOnBoarding() {
    navigate(ROUTE_ON_BOARDING)
}

fun NavGraphBuilder.onBoardingRoute(navController: NavController) {
    composable(ROUTE_ON_BOARDING) {
        OnBoardingScreen(navController)
    }
}