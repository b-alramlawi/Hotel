package com.example.hotel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hotel.ui.screen.main.ROUTE_MAIN
import com.example.hotel.ui.screen.main.mainRoute

@Composable
fun RootNavigationGraph(navController: NavHostController, isLoggedIn: Boolean) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) ROUTE_MAIN else Graph.AUTH,
        route = Graph.ROOT
    ) {
        authNavigationGraph(navController)
        mainRoute(navController)
        homeNavigationGraph(navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
    const val HOME = "home_graph"
    const val HOST = "host_graph"
}