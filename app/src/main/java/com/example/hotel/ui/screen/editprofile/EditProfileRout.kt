package com.example.hotel.ui.screen.editprofile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_EDIT_PROFILE = "edit_profile"

fun NavController.navigateToEditProfile() {
    navigate(ROUTE_EDIT_PROFILE)
}

fun NavGraphBuilder.editProfileRoute(navController: NavController) {
    composable(
        route = ROUTE_EDIT_PROFILE,
    ) {
        EditProfileScreen(navController = navController)
    }
}