package com.example.hotel.data.remote.response.dto.home

data class Hotel(
    val Location: Location,
    val id: Int,
    val images: String,
    val name: String,
    val price: String,
    val rate: String
)