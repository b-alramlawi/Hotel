package com.example.hotel.ui.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hotel.ui.navigation.MainRoute

private const val ROUTE = MainRoute.Profile

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(ROUTE) { ProfileScreen(navController) }
}
