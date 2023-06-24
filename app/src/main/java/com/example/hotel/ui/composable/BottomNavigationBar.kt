package com.example.hotel.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hotel.domain.model.BottomNavItem
import com.example.hotel.ui.theme.Gray500

@Composable
fun BottomNavigationBar(
    itemBottomNav: List<BottomNavItem>,
    navController: NavHostController,
    onItemClick: (BottomNavItem, Int) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomAppBar(
        modifier = Modifier.fillMaxHeight(0.1f),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
    {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            itemBottomNav.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        if (!selected) onItemClick(item, index)
                    },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Gray500,
                    icon = {
                        if (selected) {
                            Icon(
                                painter = item.iconSelected,
                                contentDescription = item.name,
                                modifier = Modifier.size(22.dp),
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
                        }else{
                            Icon(
                                painter = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier.size(22.dp),
                            )
                            Spacer(modifier = Modifier.fillMaxHeight(0.5f))
                        }
                    },
                    label = { Text(item.name, style = MaterialTheme.typography.caption.copy(fontWeight = if(selected) FontWeight.Bold else FontWeight.SemiBold)) },
                )
            }
        }
    }

}