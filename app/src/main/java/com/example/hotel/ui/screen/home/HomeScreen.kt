package com.example.hotel.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.home.state.HomeUiState
import com.example.hotel.ui.screen.hoteldetails.navigateToHotelDetails
import com.example.hotel.ui.screen.search.navigateToSearch
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingMedium
import com.example.hotel.ui.theme.spacingSmall
import com.example.hotel.ui.theme.spacingXMedium
import com.example.hotel.ui.theme.spacingXSmall
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
        onHotelClick = { navController.navigateToHotelDetails() },
        onSearchClick = { navController.navigateToSearch() },
        onFilterClick = {},
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onSearchChange: (String) -> Unit,
    onFilterClick: () -> Unit,
    onHotelClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSelectedChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(spacingXMedium)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacingMedium),
            modifier = Modifier.padding(horizontal = horizontalSpacing)
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

        LazyRow(
            contentPadding = PaddingValues(horizontal = horizontalSpacing),
            horizontalArrangement = Arrangement.spacedBy(spacingSmall),
        ) {
            items(state.chips) { chip ->
                CustomChip(
                    selected = chip == state.selectedChip,
                    text = stringResource(id = chip),
                    onSelectedChange = { onSelectedChange(chip) }
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = horizontalSpacing),
            horizontalArrangement = Arrangement.spacedBy(spacingMedium),
        ) {
            items(state.hotels) { hotel ->
                HotelItem(hotel = hotel, onClick = onHotelClick)
            }
        }

        Column(modifier = Modifier.padding(horizontal = horizontalSpacing)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.recently_booked),
                    style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.textPrimaryColor)
                )
                CustomTextButton(title = stringResource(id = R.string.see_all),
                    onClick = {}
                )
            }

            repeat(state.hotels.size) {
                LinearHotelItem(hotel = state.hotels[it])
            }
        }

    }
}