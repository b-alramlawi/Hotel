package com.example.hotel.domain.usecase.auth

import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.repository.auth.AuthRepository
import javax.inject.Inject

class VerifyCodeUseCase@Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(params: ParamVerifyCodeDto): Boolean {
        val response = repository.verifyCode(params)
        return response.status == "true"
    }
}

