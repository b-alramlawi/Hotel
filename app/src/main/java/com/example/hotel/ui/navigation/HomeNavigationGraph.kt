package com.example.hotel.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.example.hotel.ui.screen.bookmark.bookMarkRoute
import com.example.hotel.ui.screen.hoteldetails.ROUTE_HOTEL_DETAILS
import com.example.hotel.ui.screen.hoteldetails.hotelDetailsRoute
import com.example.hotel.ui.screen.search.searchRoute

fun NavGraphBuilder.homeNavigationGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = ROUTE_HOTEL_DETAILS,
        route = Graph.HOME
    ) {
        hotelDetailsRoute(navController)
        bookMarkRoute(navController)
        searchRoute(navController)
    }
}