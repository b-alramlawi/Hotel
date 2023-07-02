package com.example.hotel.data.remote.param

import android.net.Uri
import okhttp3.MultipartBody

data class ParamBookingDto(
    val userId: String,
    val hotelId: String,
    val checkInDate: String,
    val checkOutDate: String,
    val guestCount: String,
    val price: String,
    val paymentMethod: String = "PayPal"
)