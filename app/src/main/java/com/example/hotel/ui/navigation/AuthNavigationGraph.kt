package com.example.hotel.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.example.hotel.ui.screen.auth.createnewpassword.createNewPasswordRoute
import com.example.hotel.ui.screen.auth.setupprofile.setupProfileRoute
import com.example.hotel.ui.screen.auth.signin.signInRoute
import com.example.hotel.ui.screen.auth.signup.signUpRoute
import com.example.hotel.ui.screen.onboarding.ROUTE_ON_BOARDING
import com.example.hotel.ui.screen.onboarding.onBoardingRoute

fun NavGraphBuilder.authNavigationGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = ROUTE_ON_BOARDING,
        route = Graph.AUTH
    ) {
        onBoardingRoute(navController)
        signUpRoute(navController)
        setupProfileRoute(navController)
        signInRoute(navController)
        createNewPasswordRoute(navController)
    }
}