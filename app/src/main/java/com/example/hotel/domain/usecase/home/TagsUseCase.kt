package com.example.hotel.domain.usecase.home

import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.domain.model.Tage
import javax.inject.Inject

class TagsUseCase@Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): ArrayList<TagDto> {
        val response = repository.getAllTags()
        return response.data!!
    }
}