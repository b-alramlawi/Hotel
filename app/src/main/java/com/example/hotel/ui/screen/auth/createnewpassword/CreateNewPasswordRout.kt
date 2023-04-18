package com.example.hotel.ui.screen.auth.createnewpassword

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_CREATE_NEW_PASSWORD = "create_new_password"

fun NavController.navigateToCreateNewPassword() {
    navigate(com.example.hotel.ui.screen.auth.createnewpassword.ROUTE_CREATE_NEW_PASSWORD)
}

fun NavGraphBuilder.createNewPasswordRoute(navController: NavController) {
    composable(
        route = com.example.hotel.ui.screen.auth.createnewpassword.ROUTE_CREATE_NEW_PASSWORD,
    ) {
        com.example.hotel.ui.screen.auth.createnewpassword.CreateNewPasswordScreen(navController = navController)
    }
}