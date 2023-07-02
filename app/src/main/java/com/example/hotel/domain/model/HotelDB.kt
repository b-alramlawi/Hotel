package com.example.hotel.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HotelDB(
    @PrimaryKey val id: Int? = null,
    val hotel_id: String,
    val image: String,
    val rate: String,
    val name: String,
    val location: String,
    val price: String,
)

