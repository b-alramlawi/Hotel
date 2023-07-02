package com.example.hotel.ui.screen.home.state

import com.example.hotel.R
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.domain.model.Hotel
import com.example.hotel.domain.model.HotelDB

data class HomeUiState(
    val search: String = "",
    val chips: ArrayList<TagDto> = arrayListOf(),
    val hotels: List<com.example.hotel.data.remote.response.dto.home.Hotel> = arrayListOf(),
    val hotels2: List<com.example.hotel.data.remote.response.dto.home.Hotel> = arrayListOf(),
    val selectedChip: TagDto? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val isSave: Boolean = false,
    val errorMessage: String = "",
    val bookMarks: List<HotelDB> = arrayListOf()
)