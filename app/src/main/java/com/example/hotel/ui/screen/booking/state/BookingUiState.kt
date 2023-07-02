package com.example.hotel.ui.screen.booking.state

import com.example.hotel.R
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.domain.model.Booking
import com.example.hotel.domain.model.BookingStatus
import com.example.hotel.domain.model.Hotel

data class BookingUiState(
    val chips: ArrayList<String> = arrayListOf(
        "ongoing",
        "completed",
        "cancelled"
    ),
    val booking: ArrayList<Booking> = arrayListOf(
        Booking(
            image = R.drawable.hotel,
            name = "Samer Hotel",
            location = "Gaza, Palestine",
            status = BookingStatus.ONGOING
        ),
        Booking(
            image = R.drawable.hotel,
            name = "Samer Hotel",
            location = "Gaza, Palestine",
            status = BookingStatus.CANCELED
        ),
        Booking(
            image = R.drawable.hotel,
            name = "Samer Hotel",
            location = "Gaza, Palestine",
            status = BookingStatus.COMPLETED
        ),
    ),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
    val selectedChip: String? = chips[0],
    val dialogShowed: Boolean = false,
    val bookingStatus: ArrayList<com.example.hotel.data.remote.response.dto.home.BookingStatus> = arrayListOf()
)