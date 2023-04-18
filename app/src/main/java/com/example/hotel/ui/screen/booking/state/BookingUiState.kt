package com.example.hotel.ui.screen.booking.state

import com.example.hotel.R
import com.example.hotel.domain.model.Booking
import com.example.hotel.domain.model.BookingStatus
import com.example.hotel.domain.model.Hotel

data class BookingUiState(
    val chips: ArrayList<Int> = arrayListOf(
        R.string.ongoing,
        R.string.completed,
        R.string.canceled
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
    val selectedChip: Int = chips[0]
)