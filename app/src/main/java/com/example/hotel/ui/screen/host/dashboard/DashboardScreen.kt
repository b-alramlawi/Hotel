package com.example.hotel.ui.screen.host.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.hotel.R
import com.example.hotel.domain.model.Action
import com.example.hotel.domain.model.BottomNavItem
import com.example.hotel.domain.model.TopBarItem
import com.example.hotel.ui.composable.BottomNavigationBar
import com.example.hotel.ui.composable.HomeAppBar
import com.example.hotel.ui.navigation.HostMainNavigationGraph
import com.example.hotel.ui.navigation.MainNavigationGraph
import com.example.hotel.ui.navigation.MainRoute
import com.example.hotel.ui.screen.bookmark.navigateToBookMark
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.topPaddingValue

@Composable
fun DashboardScreen(
    navController: NavHostController,
    rootNavController: NavController
){
    DashboardContent(
        navController = navController,
        rootNavController = rootNavController
    )
}

@Composable
fun DashboardContent(
    navController: NavHostController,
    rootNavController: NavController
){
    val i = remember { mutableStateOf(0) }
    val topBarList = listOf(
        TopBarItem(
            title = stringResource(id = R.string.app_name),
            actions = arrayListOf(
                Action(
                    icon = R.drawable.bookmark_light,
                    onClick = {navController.navigateToBookMark()}
                )
            )
        ),
        TopBarItem(
            title = stringResource(id = R.string.my_booking),
            actions = arrayListOf(
                Action(
                    icon = R.drawable.search_light,
                    onClick = {}
                )
            )
        ),
    )
    Scaffold(
        modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
        topBar = {
            HomeAppBar(
                title = topBarList[i.value].title,
            ) {
                repeat(topBarList[i.value].actions.size){
                    IconButton(onClick = {topBarList[i.value].actions[it].onClick}) {
                        Icon(
                            painter = painterResource(id = topBarList[i.value].actions[it].icon),
                            contentDescription = "notification"
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                listOf(
                    BottomNavItem(
                        name = stringResource(id = R.string.home),
                        route = MainRoute.Home,
                        icon = painterResource(R.drawable.home_light),
                        iconSelected = painterResource(R.drawable.home_bold),
                    ),
                    BottomNavItem(
                        name = stringResource(id = R.string.booking),
                        route = MainRoute.Booking,
                        icon = painterResource(R.drawable.document_light),
                        iconSelected = painterResource(R.drawable.document_bold),
                    ),
                ),
                navController = navController,
                onItemClick = { item, index ->
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                        i.value = index
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .imePadding()
            ) {
                HostMainNavigationGraph(navController, rootNavController)
            }
        }
    )
}