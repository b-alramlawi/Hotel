package com.example.hotel.data.remote.response.dto.home

import com.google.gson.annotations.SerializedName

data class HotelDto(
    @SerializedName("hotels")
    val hotels: List<Hotel>,
//    val pagination: Pagination
)