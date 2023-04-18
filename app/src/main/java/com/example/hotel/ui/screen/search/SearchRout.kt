package com.example.hotel.ui.screen.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SEARCH = "search"

fun NavController.navigateToSearch() {
    navigate(ROUTE_SEARCH)
}

fun NavGraphBuilder.searchRoute(navController: NavController) {
    composable(
        route = ROUTE_SEARCH,
    ) {
        SearchScreen(navController = navController)
    }
}