package com.example.hotel.ui.screen.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_CHAT = "chat"

fun NavController.navigateToChat() {
    navigate(ROUTE_CHAT)
}

fun NavGraphBuilder.chatRoute(navController: NavController) {
    composable(
        route = ROUTE_CHAT,
    ) {
        ChatScreen(navController = navController)
    }
}