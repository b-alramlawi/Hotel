package com.example.hotel.ui.screen.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.DefaultAppBar
import com.example.hotel.ui.composable.GridHotelItem
import com.example.hotel.ui.screen.bookmark.state.BookMarkUiState

@Composable
fun BookMarkScreen(
    navController: NavController,
    viewModel: BookMarkViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    BookMarkContent(
        state = state,
        onBackClick = {navController.popBackStack()}
    )
}

@Composable
private fun BookMarkContent(
    state: BookMarkUiState,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.background).fillMaxSize()) {
        DefaultAppBar(title = stringResource(id = R.string.book_mark), onBackClick = onBackClick)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 24.dp).fillMaxSize()
        ){
            items(state.bookMarks){
                GridHotelItem(hotel = it)
            }
        }
    }
}