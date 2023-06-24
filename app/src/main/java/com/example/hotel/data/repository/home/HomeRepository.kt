package com.example.hotel.data.repository.home

import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.TagDto

interface HomeRepository {
    suspend fun getAllTags(): BaseResponse<ArrayList<TagDto>>
}