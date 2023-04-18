package com.example.hotel.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("updated_at")
    val updatedAt: String
)