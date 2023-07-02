package com.example.hotel.data.remote.service

import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.auth.UserDto
import com.example.hotel.data.remote.response.dto.home.Booking
import com.example.hotel.data.remote.response.dto.home.BookingStatus
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.remote.response.dto.home.HotelDetailsDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.remote.response.dto.home.HotelX
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

    @GET("/api/user/get-hotels")
    suspend fun getAllHotels(): BaseResponse<ArrayList<Hotel>>

    @GET("/api/user/hotels/tags/{id}")
    suspend fun getHotelByTage(
        @Path("id") id: String,
    ): BaseResponse<HotelDto>

    @GET("/api/user/hotel-detail/{id}")
    suspend fun getHotelDetails(
        @Path("id") id: String,
    ): BaseResponse<HotelX>

    @GET("/api/user/profile/{id}")
    suspend fun getCurrentUser(
        @Path("id") id: String,
    ): BaseResponse<UserDto>

    @POST("/api/user/update-profile/{id}")
    suspend fun editProfile(
        @Path("id") id: String,
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String,
        @Query("birthdate") birthdate: String,
//        @Query("email") email: String,
        @Query("phone_number") phoneNumber: String,
        @Query("gender") gender: String,
//        @Query("password") password: String,
    ): BaseResponse<UserDto>

    @GET("/api/user/image")
    suspend fun updateUserImage(
        @Part body: MultipartBody.Part,
        @Query("user_id") userId: String,
    ): BaseResponse<Any?>

    @POST("/api/user/booking")
    suspend fun bookHotel(
        @Query("user_id") userId: String,
        @Query("hotel_id") hotelId: String,
        @Query("check_in_date") checkInDate: String,
        @Query("check_out_date") checkOutDate: String,
        @Query("guest_count") guestCount: String,
        @Query("room_rate_per_night") roomRatePerNight: String,
        @Query("payment_method") paymentMethod: String,
    ): BaseResponse<Booking>

    @GET("/api/hotels/{status}")
    suspend fun getBookingByStatus(
        @Path("status") status: String,
    ): BaseResponse<ArrayList<BookingStatus>>
}