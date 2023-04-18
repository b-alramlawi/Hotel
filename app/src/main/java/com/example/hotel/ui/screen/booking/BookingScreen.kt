package com.example.hotel.ui.screen.booking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import com.example.hotel.ui.composable.BookingHotelItem
import com.example.hotel.ui.composable.CustomChip
import com.example.hotel.ui.composable.HomeAppBar
import com.example.hotel.ui.composable.LinearHotelItem
import com.example.hotel.ui.screen.booking.state.BookingUiState
import com.example.hotel.ui.screen.home.state.HomeUiState

@Composable
fun BookingScreen(
    navController: NavController,
    viewModel: BookingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    BookingContent(
        state = state,
        onSelectedChange = viewModel::onSelectedChange,
    )
}

@Composable
private fun BookingContent(
    state: BookingUiState,
    onSelectedChange: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        HomeAppBar(
            title = stringResource(id = R.string.booking),
        ){
            IconButton(onClick = {}) {
                Icon(painter = painterResource(id = R.drawable.search_light), contentDescription = "search")
            }
        }

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

        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxSize()) {
            items(state.booking) { hotel ->
                BookingHotelItem(
                    hotel = hotel,
                    modifier = Modifier.padding(horizontal = 24.dp),
                )
            }
        }
    }
}