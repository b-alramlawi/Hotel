package com.example.hotel.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.example.hotel.ui.screen.login.loginRoute
import com.example.hotel.ui.screen.onboarding.ROUTE_ON_BOARDING
import com.example.hotel.ui.screen.onboarding.onBoardingRoute
import com.example.hotel.ui.screen.register.registerRoute

fun NavGraphBuilder.authNavigationGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = ROUTE_ON_BOARDING, route = Graph.AUTH
    ) {
        onBoardingRoute(navController)
        loginRoute(navController)
        registerRoute(navController)
    }
}