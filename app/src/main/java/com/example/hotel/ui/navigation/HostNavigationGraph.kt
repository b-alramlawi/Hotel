package com.example.hotel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hotel.ui.screen.host.home.hostHomeRoute
import com.example.hotel.ui.screen.host.profile.hostProfileRoute
import com.example.hotel.ui.screen.profile.profileRoute

@Composable
fun HostMainNavigationGraph(navController: NavHostController, rootNavController: NavController) {
    NavHost(
        navController = navController,
        startDestination = HostMainRoute.Home,
        route = Graph.HOST
    ) {
        hostHomeRoute(rootNavController)
        hostProfileRoute(rootNavController)
    }
}

object HostMainRoute {
    const val Home = "host_home"
    const val Profile = "host_profile"
}