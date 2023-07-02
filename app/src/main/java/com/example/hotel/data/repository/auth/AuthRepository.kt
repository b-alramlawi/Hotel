package com.example.hotel.data.repository.auth

import com.example.hotel.data.remote.param.ParamEditProfileDto
import com.example.hotel.data.remote.param.ParamResetPasswordDto
import com.example.hotel.data.remote.param.ParamSignInDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.response.dto.auth.UserDto
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

interface AuthRepository {
    suspend fun signup(params: ParamSignUpDto): BaseResponse<SignUpDto>
    suspend fun signIn(params: ParamSignInDto): BaseResponse<SignInDto>
    suspend fun storeToken(token: String)
    suspend fun storeUserId(id: String)
    suspend fun forgotPassword(email: String): BaseResponse<Any?>
    suspend fun verifyCode(params: ParamVerifyCodeDto): BaseResponse<Any?>
    suspend fun resetPassword(params: ParamResetPasswordDto): BaseResponse<Any?>
    suspend fun uploadUserImage(file: MultipartBody.Part, userId: String): BaseResponse<Any?>
    suspend fun getCurrentUser(id: String): BaseResponse<UserDto>
    suspend fun editProfile(params: ParamEditProfileDto): BaseResponse<UserDto>
    suspend fun updateUserImage(file: MultipartBody.Part, userId: String): BaseResponse<Any?>
}