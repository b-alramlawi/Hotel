package com.example.hotel.domain.usecase.auth

import android.util.Log
import com.example.hotel.data.remote.param.ParamResetPasswordDto
import com.example.hotel.data.remote.param.ParamSignInDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.repository.auth.AuthRepository
import com.example.hotel.data.utils.Resource
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(params: ParamResetPasswordDto): Boolean {
        val response = repository.resetPassword(params)
        return response.status == "true"
    }
}

