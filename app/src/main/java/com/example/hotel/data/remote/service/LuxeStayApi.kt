package com.example.hotel.data.remote.service

import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface LuxeStayApi {

    @POST("/api/user/register")
    suspend fun signup(
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String,
        @Query("birthdate") birthdate: String,
        @Query("email") email: String,
        @Query("phone_number") phoneNumber: String,
        @Query("gender") gender: String,
        @Query("password") password: String,
    ): BaseResponse<SignUpDto>

    @POST("/api/user/login")
    suspend fun signIn(
        @Query("email") email: String,
        @Query("password") password: String,
    ): BaseResponse<SignInDto>

    @POST("/api/user/forgot-password")
    suspend fun forgotPassword(
        @Query("email") email: String,
    ): BaseResponse<Any?>

    @POST("/api/user/verify-code")
    suspend fun verifyCode(
        @Query("email") email: String,
        @Query("verificationCode") verifyCode: String,
    ): BaseResponse<Any?>

    @POST("/api/user/reset-password")
    suspend fun resetPassword(
        @Query("email") email: String,
        @Query("password") verifyCode: String,
    ): BaseResponse<Any?>

    @Multipart
    @POST("/api/user/upload-image")
    suspend fun uploadUserImage(
        @Part body: MultipartBody.Part,
        @Query("user_id") userId: String,
    ): BaseResponse<Any?>

    @GET("/api/user/get-hotel-tags")
    suspend fun getHotelTags(): BaseResponse<ArrayList<TagDto>>

    @GET("/api/user/hotels/tags/{id}")
    suspend fun getHotelByTage(
        @Path("id") id: String,
    ): BaseResponse<ArrayList<TagDto>>
}