package com.example.hotel.domain.usecase.auth

import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.repository.auth.AuthRepository
import javax.inject.Inject

class SignUpUseCase@Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(params: ParamSignUpDto): Int {
        val response = repository.signup(params)
        if(response.data != null){
            repository.storeToken(response.data.token)
            return response.data.user.id
        }
        return 0
    }
}

