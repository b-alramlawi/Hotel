package com.example.hotel.data.remote.response.dto.home


import com.google.gson.annotations.SerializedName

data class HotelDetailsDto(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)