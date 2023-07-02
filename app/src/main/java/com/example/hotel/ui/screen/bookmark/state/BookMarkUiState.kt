package com.example.hotel.ui.screen.bookmark.state

import com.example.hotel.R
import com.example.hotel.domain.model.Booking
import com.example.hotel.domain.model.BookingStatus
import com.example.hotel.domain.model.Hotel
import com.example.hotel.domain.model.HotelDB

data class BookMarkUiState(
    val bookMarks: List<HotelDB> = arrayListOf(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
)