package com.example.hotel.ui.screen.hoteldetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.response.dto.home.HotelX
import com.example.hotel.domain.usecase.home.HotelUseCase
import com.example.hotel.domain.usecase.home.HotelsDetailsUseCase
import com.example.hotel.ui.screen.hoteldetails.state.HotelDetailsUiState
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HotelDetailsViewModel @Inject constructor(
    private val getHotelsDetails: HotelsDetailsUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(HotelDetailsUiState())
    val state = _state.asStateFlow()

    init {
//        getHotelsDetail("188")
    }

    fun getHotelsDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getHotelsDetails(id)
                onChangeHotel(response)
                Log.e("samer", response.toString())
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

    private fun onChangeHotel(hotel: HotelX) {
        _state.update { it.copy(hotel = hotel) }
    }

}