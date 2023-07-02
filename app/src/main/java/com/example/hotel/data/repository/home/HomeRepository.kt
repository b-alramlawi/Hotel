package com.example.hotel.data.repository.home

import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.remote.response.dto.home.HotelDetailsDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.remote.response.dto.home.HotelX

interface HomeRepository {
    suspend fun getAllTags(): BaseResponse<ArrayList<TagDto>>
    suspend fun getAllHotels(): BaseResponse<ArrayList<Hotel>>
    suspend fun getHotelsByTags(id: String): BaseResponse<HotelDto>
    suspend fun getHotelDetails(id: String): BaseResponse<HotelX>
}