package com.example.hotel.domain.usecase.auth

import com.example.hotel.data.remote.param.ParamEditProfileDto
import com.example.hotel.data.repository.auth.AuthRepository
import javax.inject.Inject

class EditProfileUseCase@Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(params: ParamEditProfileDto): Int {
        val response = repository.editProfile(params)
        if(response.data != null){
//            repository.storeToken(response.data.token)
            return response.data.id
        }
        return 0
    }
}

