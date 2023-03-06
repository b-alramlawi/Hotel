package com.example.hotel.ui.screen.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.hotel.ui.navigation.MainRoute

private const val ROUTE = MainRoute.Chat


fun NavController.navigateToConversations() {
    navigate(ROUTE)
}

fun NavGraphBuilder.chatRoute(navController: NavController) {
    composable(ROUTE) {
        ChatScreen(navController)
    }
}