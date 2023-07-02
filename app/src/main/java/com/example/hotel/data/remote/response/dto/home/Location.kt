package com.example.hotel.data.remote.response.dto.home

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("location_name")
    val locationName: String,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("region")
    val region: Region,
    @SerializedName("region_id")
    val regionId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)