package com.example.hotel.data.remote.param

import android.net.Uri
import okhttp3.MultipartBody

data class ParamSignInDto(
    val email: String,
    val password: String
)