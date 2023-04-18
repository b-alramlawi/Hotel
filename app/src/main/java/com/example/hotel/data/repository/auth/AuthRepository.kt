package com.example.hotel.data.repository.auth

import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignUpDto

interface AuthRepository {
    suspend fun signup(params: ParamSignUpDto): BaseResponse<SignUpDto>
    suspend fun storeToken(token: String)
}