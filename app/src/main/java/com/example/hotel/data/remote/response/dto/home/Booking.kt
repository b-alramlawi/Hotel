package com.example.hotel.data.remote.response.dto.home


import com.google.gson.annotations.SerializedName

data class Booking(
    @SerializedName("check_in_date")
    val checkInDate: String,
    @SerializedName("check_out_date")
    val checkOutDate: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("guest_count")
    val guestCount: String,
    @SerializedName("hotel_id")
    val hotelId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("payment_method")
    val paymentMethod: String,
    @SerializedName("payment_status")
    val paymentStatus: String,
    @SerializedName("room_rate_per_night")
    val roomRatePerNight: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String
)