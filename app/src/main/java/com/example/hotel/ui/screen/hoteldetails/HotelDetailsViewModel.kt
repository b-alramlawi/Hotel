package com.example.hotel.ui.screen.hoteldetails

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.hoteldetails.state.HotelDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HotelDetailsViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(HotelDetailsUiState())
    val state = _state.asStateFlow()

}