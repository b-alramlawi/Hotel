package com.example.hotel.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation

fun NavGraphBuilder.detailsNavigationGraph(navController: NavHostController) {
    navigation(startDestination = "", route = Graph.DETAILS) {}
}