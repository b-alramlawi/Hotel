package com.example.hotel.ui.screen.home

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    fun onChangeSearch(newValue: String) {
        _state.update { it.copy(search = newValue) }
    }

    fun onSelectedChange(newValue: Int) {
        _state.update { it.copy(selectedChip = newValue) }
    }
}