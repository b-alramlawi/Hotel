package com.example.hotel.ui.screen.search

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    fun onSearchChange(newValue: String) {
        _state.update { it.copy(search = newValue) }
    }

    fun onSelectCountryChange(newValue: String) {
        _state.update { it.copy(selectedCountry = newValue) }
    }

    fun onSelectRateChange(newValue: String) {
        _state.update { it.copy(selectedRate = newValue) }
    }

    fun onSelectSortChange(newValue: String) {
        _state.update { it.copy(selectedSort = newValue) }
    }

    fun onSelectFacilityChange(newValue: String) {
        state.value.selectedFacility.add(newValue)
        _state.update { it.copy(selectedFacility = state.value.selectedFacility) }
    }

}