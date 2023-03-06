package com.example.hotel.ui.composable.bottom_navigation_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hotel.ui.theme.Gray500

@Composable
fun BottomNavigationBar(
    itemBottomNav: List<BottomNavItem>,
    navController: NavHostController,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomAppBar(
        modifier = Modifier
            .height(60.dp),
        backgroundColor = MaterialTheme.colors.background,
    )
    {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            itemBottomNav.forEachIndexed { _, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        if (!selected) onItemClick(item)
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Gray500,
                    icon = {
                        if (selected) ComposableNavigationIcon(item) else ComposableNavigationIcon(
                            item
                        )
                    },
                    label = { Text(item.name) },
                )

            }
        }
    }

}