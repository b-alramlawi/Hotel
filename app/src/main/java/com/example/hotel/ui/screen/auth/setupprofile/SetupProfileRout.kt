package com.example.hotel.ui.screen.auth.setupprofile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE_SETUP_PROFILE = "setup_profile"

fun NavController.navigateToSetupProfile(email: String, password: String) {
    navigate("${ROUTE_SETUP_PROFILE}/$email/$password")
}

fun NavGraphBuilder.setupProfileRoute(navController: NavController) {
    composable(
        route = "$ROUTE_SETUP_PROFILE/{${SetupProfileScreenArgs.EMAIL}}/{${SetupProfileScreenArgs.PASSWORD}}",
        arguments = listOf(
            navArgument(name = SetupProfileScreenArgs.EMAIL) { NavType.StringType },
            navArgument(name = SetupProfileScreenArgs.PASSWORD) { NavType.StringType },
        )
    ) {
        SetupProfileScreen(navController = navController, arguments = it.arguments!!)
    }
}

class SetupProfileScreenArgs(savedStateHandle: SavedStateHandle) {
    val email: String? = savedStateHandle[EMAIL]
    val password: String? = savedStateHandle[PASSWORD]

    companion object {
        const val EMAIL = "email"
        const val PASSWORD = "password"
    }
}