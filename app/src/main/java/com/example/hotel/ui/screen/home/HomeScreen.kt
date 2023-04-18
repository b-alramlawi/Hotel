package com.example.hotel.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.bookmark.navigateToBookMark
import com.example.hotel.ui.screen.home.state.HomeUiState
import com.example.hotel.ui.screen.hoteldetails.navigateToHotelDetails
import com.example.hotel.ui.screen.search.navigateToSearch
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        onSearchChange = viewModel::onChangeSearch,
        onSelectedChange = viewModel::onSelectedChange,
        onHotelClick = {navController.navigateToHotelDetails()},
        onSearchClick = {navController.navigateToSearch()},
        onFilterClick = {},
        onBookMarkClick = {navController.navigateToBookMark()},
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onSearchChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    onHotelClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSelectedChange: (Int) -> Unit
) {
    Column {
        HomeAppBar(
            title = stringResource(id = R.string.app_name),
        ) {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.notification_light),
                    contentDescription = "notification"
                )
            }
            IconButton(onClick = onBookMarkClick) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark_light),
                    contentDescription = "bookmark"
                )
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            item() {
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.hello) + " Samer",
                        style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
                    SearchTextFiled(
                        value = state.search,
                        onFilterClick = onFilterClick,
                        onValueChange = onSearchChange,
                        enable = false,
                        onSearchClick = onSearchClick
                    )
                }
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(start = 24.dp)
                ) {
                    items(state.chips) { chip ->
                        CustomChip(
                            selected = chip == state.selectedChip,
                            text = stringResource(id = chip),
                            onSelectedChange = { onSelectedChange(chip) }
                        )
                    }
                }
            }

            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.padding(start = 24.dp)
                ) {
                    items(state.hotels) { hotel ->
                        HotelItem(hotel = hotel, onClick = onHotelClick)
                    }
                }
            }

            item {
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.recently_booked),
                        style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = stringResource(id = R.string.see_all),
                            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primary)
                        )
                    }
                }

            }

            items(state.hotels) { hotel ->
                LinearHotelItem(hotel = hotel, modifier = Modifier.padding(horizontal = 24.dp))
            }
        }
    }

}