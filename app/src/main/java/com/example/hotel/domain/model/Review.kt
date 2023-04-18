package com.example.hotel.domain.model

data class Review(
    val image: String,
    val name: String,
    val data: String,
    val rate: Float,
    val details: String
)