package com.example.hotel.domain.model

data class Booking(
    val image: Int,
    val name: String,
    val location: String,
    val status: String
)

object BookingStatus {
    const val ONGOING = "ongoing"
    const val COMPLETED = "completed"
    const val CANCELED = "canceled"
}