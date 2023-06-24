package com.example.hotel.data.remote.response.dto.home

data class HotelDto(
    val hotels: List<Hotel>,
    val pagination: Pagination
)