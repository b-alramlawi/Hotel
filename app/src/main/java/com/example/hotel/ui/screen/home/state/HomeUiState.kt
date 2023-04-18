package com.example.hotel.ui.screen.home.state

import com.example.hotel.R
import com.example.hotel.domain.model.Hotel

data class HomeUiState(
    val search: String = "",
    val chips: ArrayList<Int> = arrayListOf(
        R.string.recommended,
        R.string.popular,
        R.string.nearby
    ),
    val hotels: ArrayList<Hotel> = arrayListOf(
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
        ),
        Hotel(
            R.drawable.hotel,
            4.5f,
            "Samer Hotel",
            "Gaza - Palestine",
            50.9
        )
    ),
    val selectedChip: Int = chips[0],
)