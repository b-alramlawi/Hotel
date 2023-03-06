package com.example.hotel.ui.screen.main

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hotel.ui.navigation.Graph

private const val ROUTE = Graph.MAIN

fun NavController.navigateToMain() {
    navigate(ROUTE)
}

@ExperimentalMaterialApi
fun NavGraphBuilder.mainRoute(navController: NavController) {

    composable(ROUTE) {
        MainScreen(
            navController = rememberNavController(),
            rootNavController = navController
        )
    }
}