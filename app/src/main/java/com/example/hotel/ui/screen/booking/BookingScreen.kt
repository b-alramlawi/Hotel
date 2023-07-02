package com.example.hotel.ui.screen.booking

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.hotel.domain.model.DialogContent
import com.example.hotel.ui.composable.BookingHotelItem
import com.example.hotel.ui.composable.CustomChip
import com.example.hotel.ui.composable.HomeAppBar
import com.example.hotel.ui.composable.LinearHotelItem
import com.example.hotel.ui.composable.SuccessDialog
import com.example.hotel.ui.screen.auth.signin.navigateToSignIn
import com.example.hotel.ui.screen.booking.state.BookingUiState
import com.example.hotel.ui.screen.home.state.HomeUiState
import com.example.hotel.ui.theme.textFifthColor

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
    onSelectedChange: (String) -> Unit
) {

        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(start = 24.dp)
            ) {
                items(state.chips) { chip ->
                    CustomChip(
                        selected = chip == state.selectedChip,
                        text = chip,
                        onSelectedChange = { onSelectedChange(chip) }
                    )
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.isFailed) {
                Text(
                    text = stringResource(id = R.string.error),
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
                )
            } else {
                Log.e("samer", "Done")
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
                items(state.bookingStatus) { hotel ->
                    BookingHotelItem(
                        hotel = hotel,
                        modifier = Modifier.padding(horizontal = 24.dp),
                    )
                }
            }
        }
    }

}