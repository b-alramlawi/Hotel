package com.example.hotel.domain.usecase.auth

import android.util.Log
import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.auth.UserDto
import com.example.hotel.data.remote.response.dto.home.HotelDetailsDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.remote.response.dto.home.HotelX
import com.example.hotel.data.repository.auth.AuthRepository
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.domain.model.Tage
import javax.inject.Inject

class ProfileUseCase@Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(id: String): UserDto {
        val response = repository.getCurrentUser(id)
        return response.data!!
    }
}