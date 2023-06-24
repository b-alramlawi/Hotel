package com.example.hotel.data.remote.response.dto.home

data class Pagination(
    val current_page: Int,
    val last_page: Int,
    val per_page: Int,
    val total: Int
)