package com.example.hotel.data.remote.response.dto.home


import com.google.gson.annotations.SerializedName

data class BookingStatus(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("payment_status")
    val paymentStatus: String
)