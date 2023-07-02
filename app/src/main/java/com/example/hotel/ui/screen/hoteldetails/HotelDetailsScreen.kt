package com.example.hotel.ui.screen.hoteldetails

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R
import com.example.hotel.ui.composable.CustomButton
import com.example.hotel.ui.composable.FacilityItem
import com.example.hotel.ui.composable.ReviewItem
import com.example.hotel.ui.composable.home.RoundedRating
import com.example.hotel.ui.screen.booking.BookingViewModel
import com.example.hotel.ui.screen.confirmbooking.navigateToConfirmBooking
import com.example.hotel.ui.screen.hoteldetails.state.HotelDetailsUiState
import com.example.hotel.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HotelDetailsScreen(
    navController: NavController,
    arguments: Bundle,
    viewModel: HotelDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val pagerState = rememberPagerState()

    LaunchedEffect(Unit){
        viewModel.getHotelsDetail(arguments.getString("id")!!)
    }

    if(state.isLoading){
        CircularProgressIndicator()
    }else if(state.isFailed){
        Text(
            text = stringResource(id = R.string.error),
            style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
        )
    } else if(state.hotel != null){
        Box(modifier = Modifier.padding(bottom = bottomPaddingValue())) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)
            ) {
                item {
                    Box() {
                        HorizontalPager(
                            count = state.images.size,
                            state = pagerState,
                        ) {
                            SubcomposeAsyncImage(
                                model ="https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
                                contentDescription = "details_picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp),
                            )
                        }
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                        ) {
                            items(state.images.size) {
                                Box(
                                    modifier = Modifier
                                        .height(10.dp)
                                        .width(10.dp)
                                        .background(
                                            shape = CircleShape,
                                            color = if (pagerState.currentPage == it) MaterialTheme.colors.primary else MaterialTheme.colors.onSecondary
                                        ),
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    text = state.hotel!!.name,
                                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.textPrimaryColor)
                                )
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.location_bold),
                                        contentDescription = "location",
                                        tint = MaterialTheme.colors.primary
                                    )
                                    Text(
                                        text = state.hotel!!.location.locationName,
                                        style = MaterialTheme.typography.button.copy(
                                            color = MaterialTheme.colors.textPrimaryColor,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                }
                            }
                            RoundedRating(rate = state.hotel!!.rate)
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
                            )
                            LazyVerticalGrid(
                                modifier = Modifier.height(120.dp),
                                columns = GridCells.Adaptive(minSize = 64.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.hotel!!.tags) {
                                    FacilityItem(facility = it)
                                }
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = "description",
                                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
                            )
                            Text(
                                text = state.hotel!!.description,
                                style = MaterialTheme.typography.button.copy(
                                    color = MaterialTheme.colors.textPrimaryColor,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Text(
                                text = stringResource(id = R.string.facilities),
                                style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
                            )
                            LazyVerticalGrid(
                                modifier = Modifier.height(120.dp),
                                columns = GridCells.Adaptive(minSize = 64.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.hotel!!.facilities) {
                                    FacilityItem(facility = it)
                                }
                            }
                        }

//                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
//                        Text(
//                            text = stringResource(id = R.string.review),
//                            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
//                        )
//                        (state.review).forEach {
//                            ReviewItem(it)
//                        }
//                    }
                    }
                }
            }

            Card(
                modifier = Modifier.align(Alignment.BottomCenter),
                backgroundColor = MaterialTheme.colors.background,
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                border = BorderStroke(color = MaterialTheme.colors.secondaryVariant, width = 1.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = state.hotel!!.price,
                            style = MaterialTheme.typography.h3.copy(color = MaterialTheme.colors.primary)
                        )
                        Text(
                            text = "/ night",
                            style = MaterialTheme.typography.button.copy(
                                MaterialTheme.colors.textSecondaryColor,
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    CustomButton(
                        title = "Book Now",
                        color = MaterialTheme.colors.primary,
                        textColor = White,
                        onClick = {navController.navigateToConfirmBooking(hotelId = state.hotel!!.id.toString(), price = state.hotel!!.price)}
                    )
                }
            }
        }
    }

}