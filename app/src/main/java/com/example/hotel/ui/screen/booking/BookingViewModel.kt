package com.example.hotel.ui.screen.booking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.BookingStatus
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.domain.usecase.booking.BookingUseCase
import com.example.hotel.domain.usecase.bookmark.BookMarkUseCases
import com.example.hotel.domain.usecase.home.TagsUseCase
import com.example.hotel.ui.screen.booking.state.BookingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val getBookingByStatus: BookingUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(BookingUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllBookingByStatus("ongoing")
        }
    }

    fun onSelectedChange(newValue: String) {
        _state.update { it.copy(selectedChip = newValue) }
        Log.e("samer", newValue)
        getAllBookingByStatus(_state.value.selectedChip!!)
    }

    private fun getAllBookingByStatus(status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getBookingByStatus(status)
                onChangeBookingStatus(response)
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
                Log.e("samer",e.message.toString())
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

    private fun onChangeBookingStatus(newValue: ArrayList<BookingStatus>) {
        _state.update { it.copy(bookingStatus = newValue) }
    }

}