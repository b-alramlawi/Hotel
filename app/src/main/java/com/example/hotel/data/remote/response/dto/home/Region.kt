package com.example.hotel.data.remote.response.dto.home

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("region_name")
    val regionName: String,
    @SerializedName("updated_at")
    val updatedAt: String
)