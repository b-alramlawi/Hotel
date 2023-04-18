package com.example.hotel.data.remote.service

import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface LuxeStayApi {

    @Multipart
    @POST("/hotel_api/public/api/register")
    suspend fun signup(
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String,
        @Query("birthdate") birthdate: String,
        @Query("email") email: String,
        @Query("phone_number") phoneNumber: String,
        @Query("gender") gender: String,
        @Part image: MultipartBody.Part,
        @Query("password") password: String,
    ):BaseResponse<SignUpDto>
}