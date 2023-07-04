package com.example.hotel.data.remote.response.dto.home

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("Location")
    val Location: Location,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    var images: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("rate")
    val rate: String
)