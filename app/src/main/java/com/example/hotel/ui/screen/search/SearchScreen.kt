package com.example.hotel.ui.screen.search

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.FilterBottomSheet
import com.example.hotel.ui.composable.GenderBottomSheet
import com.example.hotel.ui.composable.LinearHotelItem
import com.example.hotel.ui.composable.RecentlySearchItem
import com.example.hotel.ui.composable.SearchTextFiled
import com.example.hotel.ui.screen.booking.BookingViewModel
import com.example.hotel.ui.screen.hoteldetails.navigateToHotelDetails
import com.example.hotel.ui.screen.search.state.SearchUiState
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor
import com.example.hotel.ui.theme.topPaddingValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    SearchContent(
        state = state,
        onFilterClick = { coroutineScope.launch { modalSheetState.show() } },
        onSearchChange = viewModel::onSearchChange,
        onSelectCountryChange = viewModel::onSelectCountryChange,
        onSelectSortChange = viewModel::onSelectSortChange,
        onSelectRateChange = viewModel::onSelectRateChange,
        onSelectFacilityChange = viewModel::onSelectFacilityChange,
        modalSheetState = modalSheetState,
        onApplyClick = {},
        onHotelClick = { navController.navigateToHotelDetails(it) },
        onCancelClick = { coroutineScope.launch { modalSheetState.hide() } },
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchContent(
    state: SearchUiState,
    onFilterClick: () -> Unit,
    onApplyClick: () -> Unit,
    onCancelClick: () -> Unit,
    onHotelClick: (String) -> Unit,
    onSearchChange: (String) -> Unit,
    onSelectCountryChange: (String) -> Unit,
    onSelectSortChange: (String) -> Unit,
    onSelectRateChange: (String) -> Unit,
    onSelectFacilityChange: (String) -> Unit,
    modalSheetState: ModalBottomSheetState,
) {
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetContent = {
            FilterBottomSheet(
                state = state,
                onSelectCountryChange = onSelectCountryChange,
                onSelectSortChange = onSelectSortChange,
                onSelectRateChange = onSelectRateChange,
                onSelectFacilityChange = onSelectFacilityChange,
                onApplyClick = onApplyClick,
                onCancelClick = onCancelClick,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .padding(bottom = bottomPaddingValue(), top = topPaddingValue())
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp),
            ) {
                SearchTextFiled(
                    value = state.search,
                    onFilterClick = onFilterClick,
                    onValueChange = onSearchChange
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Result",
                    style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.textPrimaryColor)
                )
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    items(state.filteredHotel) {
                        LinearHotelItem(
                            hotel = it,
                            onClick = { onHotelClick(it.id.toString()) },
                            onBookMarkClick = {},
//                            isSaved = false
                        )
                    }
                }
            }
        }
    }
}