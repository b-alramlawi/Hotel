package com.example.hotel.ui.screen.bookmark

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_BOOK_MARK = "book_mark"

fun NavController.navigateToBookMark() {
    navigate(ROUTE_BOOK_MARK)
}

fun NavGraphBuilder.bookMarkRoute(navController: NavController) {
    composable(route = ROUTE_BOOK_MARK) {
        BookMarkScreen(navController = navController)
    }
}