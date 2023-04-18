package com.example.hotel.data.remote.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> (
    @SerializedName("status")
    val status: String?,
    @SerializedName("data")
    val data: T?,
    @SerializedName("message")
    val message: String?,
)