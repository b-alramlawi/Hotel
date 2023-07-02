package com.example.hotel.ui.screen.confirmbooking

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamBookingDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.utils.Constants
import com.example.hotel.domain.usecase.auth.ProfileUseCase
import com.example.hotel.domain.usecase.booking.AddBookingUseCase
import com.example.hotel.ui.screen.booking.state.BookingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ConfirmBookingViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val bookHotel: AddBookingUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(ConfirmBookingUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            onChangeUserId(getUserId()!!)
        }
    }

    fun onDismissRequest() {
        _state.update { it.copy(dialogShowed = !_state.value.dialogShowed) }
    }

    fun onDismissRequest2() {
        _state.update { it.copy(dialogErrorShowed = !_state.value.dialogErrorShowed) }
    }

    fun onChangeCheckIn(newValue: String) {
        _state.update { it.copy(checkIn = newValue) }
    }

    fun onChangeCheckOut(newValue: String) {
        _state.update { it.copy(checkOut = newValue) }
    }

    fun onChangeGuest(newValue: String) {
        _state.update { it.copy(gustCount = newValue) }
    }

    fun onChangeUserId(newValue: String) {
        _state.update { it.copy(userId = newValue) }
    }

    fun onBookingClick(hotelId: String, price: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val params = ParamBookingDto(
                    userId = state.value.userId,
                    hotelId = hotelId,
                    checkInDate = state.value.checkIn,
                    checkOutDate = state.value.checkOut,
                    guestCount = state.value.gustCount,
                    price = price,
                )
                val status = bookHotel(params)
                if (status) {
                    onDismissRequest()
                }
            } catch (e: IOException) {
                onFailed("Check your internet")
                Log.e("samer","Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
                onDismissRequest2()
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

    fun isContinueButtonEnable(): Boolean {
        return (
                _state.value.checkOut.isNotBlank() &&
                        _state.value.checkIn.isNotBlank() &&
                        _state.value.gustCount.isNotBlank()
                )
    }

    private suspend fun getUserId(): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(Constants.USER_ID)]
    }
}