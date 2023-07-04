package com.example.hotel.data.remote.response.dto.home


import com.google.gson.annotations.SerializedName

data class HotelX(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("facilities")
    val facilities: List<String>,
    @SerializedName("host_id")
    val hostId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    var images: List<String>,
    @SerializedName("Location")
    val location: LocationX,
    @SerializedName("location_id")
    val locationId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rate")
    val rate: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("updated_at")
    val updatedAt: String
)