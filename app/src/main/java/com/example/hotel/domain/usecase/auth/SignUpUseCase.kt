package com.example.hotel.domain.usecase.auth

import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.repository.auth.AuthRepository
import com.example.hotel.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase@Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(params: ParamSignUpDto): BaseResponse<SignUpDto> {

        val response = repository.signup(params)
        if(response.status == "true"){
            repository.storeToken(response.data?.token!!)
        }
        return response
    }
}

