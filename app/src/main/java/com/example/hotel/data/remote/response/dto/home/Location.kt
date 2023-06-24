package com.example.hotel.data.remote.response.dto.home

data class Location(
    val created_at: String,
    val id: Int,
    val latitude: Double,
    val location_name: String,
    val longitude: Double,
    val region: Region,
    val region_id: Int,
    val updated_at: String
)