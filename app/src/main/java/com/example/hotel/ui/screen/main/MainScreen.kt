package com.example.hotel.ui.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.hotel.R
import com.example.hotel.domain.model.BottomNavItem
import com.example.hotel.ui.composable.BottomNavigationBar
import com.example.hotel.ui.navigation.MainNavigationGraph
import com.example.hotel.ui.navigation.MainRoute

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    rootNavController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                listOf(
                    BottomNavItem(
                        name = "Home",
                        route = MainRoute.Home,
                        icon = painterResource(R.drawable.home_light),
                        iconSelected = painterResource(R.drawable.home_bold),
                    ),
                    BottomNavItem(
                        name = "Booking",
                        route = MainRoute.Booking,
                        icon = painterResource(R.drawable.document_light),
                        iconSelected = painterResource(R.drawable.document_bold),
                    ),
                    BottomNavItem(
                        name = "Chat",
                        route = MainRoute.Chat,
                        icon = painterResource(R.drawable.chat_light),
                        iconSelected = painterResource(R.drawable.chat_bold),
                    ),
                    BottomNavItem(
                        name = "Profile",
                        route = MainRoute.Profile,
                        icon = painterResource(R.drawable.profile_light),
                        iconSelected = painterResource(R.drawable.profile_bold),
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .consumedWindowInsets(innerPadding)
                    .padding(innerPadding)
                    .imePadding()
            ) {
                MainNavigationGraph(navController, rootNavController)
            }
        }
    )
}
