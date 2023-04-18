package com.example.hotel.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.bookmark.state.BookMarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(BookMarkUiState())
    val state = _state.asStateFlow()

}