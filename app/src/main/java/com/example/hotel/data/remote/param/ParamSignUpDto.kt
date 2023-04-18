package com.example.hotel.data.remote.param

import android.net.Uri
import okhttp3.MultipartBody

data class ParamSignUpDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val gender: String,
    val birthDate: String,
    val image: MultipartBody.Part,
    val password: String
)