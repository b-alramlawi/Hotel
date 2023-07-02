package com.example.hotel.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.domain.usecase.home.HotelUseCase
import com.example.hotel.ui.screen.search.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllHotel: HotelUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()

    init {
        getAllHotels()
    }

    fun onSearchChange(newValue: String) {
        _state.update { it.copy(search = newValue) }
        if(state.value.filteredHotel.isEmpty()){
            getAllHotels()
        }
        filteredHotel(state.value.search)
    }

    private fun getAllHotels() {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getAllHotel()
                onChangeFilteredHotel(response)
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
            }
            cancelLoading()
        }
    }

    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun setErrorMessage(message: String) {
        _state.update { it.copy(errorMessage = message) }
    }

    private fun cancelLoading() {
        _state.update { it.copy(isLoading = false) }
    }

    private fun onSuccess() {
        _state.update { it.copy(isSuccess = true) }
    }

    private fun onFailed(message: String) {
        setErrorMessage(message)
        _state.update { it.copy(isFailed = true) }
    }

    fun clearErrorMessage() {
        _state.update { it.copy(errorMessage = "") }
    }

    private fun filteredHotel(query: String){
        val filteredHotel = state.value.filteredHotel.filter {
            it.name.contains(query, ignoreCase = true)
        }
        onChangeFilteredHotel(filteredHotel)
    }

    fun onSelectCountryChange(newValue: String) {
        _state.update { it.copy(selectedCountry = newValue) }
    }

    private fun onChangeFilteredHotel(newValue: List<Hotel>) {
        _state.update { it.copy(filteredHotel = newValue) }
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