package com.example.hotel.data.remote.response.dto.home


import com.google.gson.annotations.SerializedName

data class LocationX(
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
    val region: RegionX,
    @SerializedName("region_id")
    val regionId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)