package com.example.hotel.domain.usecase.home

import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.domain.model.Tage
import javax.inject.Inject

class HotelUseCase@Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): ArrayList<Hotel> {
        val response = repository.getAllHotels()
        return response.data!!
    }
}