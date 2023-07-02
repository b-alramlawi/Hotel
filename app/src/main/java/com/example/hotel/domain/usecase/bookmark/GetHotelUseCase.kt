package com.example.hotel.domain.usecase.bookmark

import com.example.hotel.data.repository.bookmark.BookMarkRepository
import com.example.hotel.domain.model.HotelDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetHotels(
    private val repository: BookMarkRepository
) {
    operator fun invoke(): List<HotelDB> {
        return repository.getHotels()
    }
}