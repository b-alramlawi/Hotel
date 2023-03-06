package com.example.hotel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hotel.ui.screen.booking.bookingRoute
import com.example.hotel.ui.screen.chat.chatRoute
import com.example.hotel.ui.screen.home.homeRoute
import com.example.hotel.ui.screen.profile.profileRoute

@Composable
fun MainNavigationGraph(navController: NavHostController, rootNavController: NavController) {
    NavHost(
        navController = navController,
        startDestination = MainRoute.Home,
        route = Graph.MAIN
    ) {
        homeRoute(rootNavController)
        bookingRoute(rootNavController)
        chatRoute(rootNavController)
        profileRoute(rootNavController)
    }
}

object MainRoute {
    const val Home = "home"
    const val Booking = "booking"
    const val Chat = "chat"
    const val Profile = "profile"
}