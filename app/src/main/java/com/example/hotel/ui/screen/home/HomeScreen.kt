package com.example.hotel.ui.screen.home

import android.os.Bundle
import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.domain.model.HotelDB
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.home.state.HomeUiState
import com.example.hotel.ui.screen.hoteldetails.navigateToHotelDetails
import com.example.hotel.ui.screen.search.navigateToSearch
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingMedium
import com.example.hotel.ui.theme.spacingSmall
import com.example.hotel.ui.theme.spacingXMedium
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    viewModel.getAllBookMark()

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
                onFilterClick = { },
                onValueChange = viewModel::onChangeSearch,
                enable = false,
                onSearchClick = { navController.navigateToSearch() }
            )
        }

        if (state.isLoading) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator()
            }
        } else if (state.isFailed) {
            Box{
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.error),
                    style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
                )
            }
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = horizontalSpacing),
                horizontalArrangement = Arrangement.spacedBy(spacingSmall),
            ) {
                items(state.chips) { chip ->
                    CustomChip(
                        selected = chip == state.selectedChip!!,
                        text = chip.name,
                        onSelectedChange = { viewModel.onSelectedChange(chip) }
                    )
                }
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = horizontalSpacing),
                horizontalArrangement = Arrangement.spacedBy(spacingMedium),
            ) {
                items(state.hotels) { hotel ->
                    HotelItem(
                        hotel = hotel,
                        onClick = { navController.navigateToHotelDetails(hotel.id.toString()) })
                }
            }

            Column(modifier = Modifier.padding(horizontal = horizontalSpacing)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "All",
                        style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
//                    CustomTextButton(title = stringResource(id = R.string.see_all),
//                        onClick = {}
//                    )
                }

                repeat(state.hotels2.size) { hotel ->
                    LinearHotelItem(
                        hotel = state.hotels2[hotel],
                        onClick = { navController.navigateToHotelDetails(state.hotels2[hotel].id.toString()) },
                        onBookMarkClick = {
                            viewModel.onBookMarkClick(
                                HotelDB(
                                    hotel_id = state.hotels2[hotel].id.toString(),
                                    image = state.hotels2[hotel].images,
                                    location = state.hotels2[hotel].Location.locationName,
                                    price = state.hotels2[hotel].price,
                                    name = state.hotels2[hotel].name,
                                    rate = state.hotels2[hotel].rate
                                )
                            )
                            viewModel.onChangeIsSave()
                        },
                    )
                }
            }
        }
    }
}