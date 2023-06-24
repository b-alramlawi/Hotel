package com.example.hotel.ui.screen.home.state

import com.example.hotel.R
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.domain.model.Hotel

data class HomeUiState(
    val search: String = "",
    val chips: ArrayList<TagDto> = arrayListOf(
        TagDto(
            id = "0",
            name = "Recommended",
            createdAt = "",
            updatedAt = ""
        ),
        TagDto(
            id = "0",
            name = "Popular",
            createdAt = "",
            updatedAt = ""
        ),
        TagDto(
            id = "0",
            name = "Top Rated",
            createdAt = "",
            updatedAt = ""
        )
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
    val selectedChip: TagDto? = if (chips.isNotEmpty()) chips[0] else null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
)