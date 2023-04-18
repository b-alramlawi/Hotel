package com.example.hotel.ui.screen.booking

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.booking.state.BookingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(BookingUiState())
    val state = _state.asStateFlow()

    fun onSelectedChange(newValue: Int) {
        _state.update { it.copy(selectedChip = newValue) }
    }

}