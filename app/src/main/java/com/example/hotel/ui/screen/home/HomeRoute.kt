package com.example.hotel.ui.screen.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hotel.ui.navigation.MainRoute

private const val ROUTE = MainRoute.Home
fun NavController.navigateToHomeScreen(){
    popBackStack()
}
fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(ROUTE) { HomeScreen(navController) }
}