package com.example.hotel.ui.screen.bookmark.state

import com.example.hotel.R
import com.example.hotel.domain.model.Booking
import com.example.hotel.domain.model.BookingStatus
import com.example.hotel.domain.model.Hotel

data class BookMarkUiState(
    val bookMarks: ArrayList<Hotel> = arrayListOf(
        Hotel(
            R.drawable.hotel,
            4.5f,
            "Samer Hotel",
            "Gaza",
            50.9
        ),
        Hotel(
            R.drawable.hotel,
            4.5f,
            "Samer Hotel",
            "Gaza - Palestine",
            50.9
        ),
        Hotel(
            R.drawable.hotel,
            4.5f,
            "Samer Hotel",
            "Gaza - Palestine",
            50.9
        )
    )
)