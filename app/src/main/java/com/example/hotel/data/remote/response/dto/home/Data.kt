package com.example.hotel.data.remote.response.dto.home


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("hotel")
    val hotel: HotelX
)