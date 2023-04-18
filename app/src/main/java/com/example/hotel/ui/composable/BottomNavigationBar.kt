package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hotel.domain.model.BottomNavItem
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
        elevation = 0.dp
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
                        if (selected) {
                            Icon(
                                painter = item.iconSelected,
                                contentDescription = item.name,
                                modifier = Modifier.size(24.dp),
                            )
                        }else{
                            Icon(
                                painter = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    },
                    label = { Text(item.name) },
                )

            }
        }
    }

}