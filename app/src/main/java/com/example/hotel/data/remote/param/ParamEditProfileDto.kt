package com.example.hotel.data.remote.param

import android.net.Uri
import okhttp3.MultipartBody

data class ParamEditProfileDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val gender: String,
    val birthDate: String,
)