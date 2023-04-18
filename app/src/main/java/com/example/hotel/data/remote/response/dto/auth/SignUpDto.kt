package com.example.hotel.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName

data class SignUpDto(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: UserDto
)