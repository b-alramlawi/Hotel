package com.example.hotel.ui.screen.confirmbooking

import com.example.hotel.R
import com.example.hotel.domain.model.Booking
import com.example.hotel.domain.model.BookingDetails
import com.example.hotel.domain.model.BookingStatus
import com.example.hotel.domain.model.Hotel

data class ConfirmBookingUiState(
    val checkIn: String = "",
    val checkOut: String = "",
    val gustCount: String = "",
    val userId: String = "",
    val btnEnable: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
    val dialogShowed: Boolean = false,
    val dialogErrorShowed: Boolean = false,
)