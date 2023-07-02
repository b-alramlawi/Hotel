package com.example.hotel.domain.usecase.home

import android.util.Log
import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.HotelDetailsDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.remote.response.dto.home.HotelX
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.domain.model.Tage
import javax.inject.Inject

class HotelsDetailsUseCase@Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String): HotelX {
        val response = repository.getHotelDetails(id)
        Log.e("samer", response.status.toString())
        Log.e("samer", response.message.toString())

        return response.data!!
    }
}