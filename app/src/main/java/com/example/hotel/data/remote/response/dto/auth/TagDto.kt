package com.example.hotel.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName

data class TagDto (
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)