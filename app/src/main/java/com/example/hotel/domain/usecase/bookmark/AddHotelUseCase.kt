package com.example.hotel.domain.usecase.bookmark

import com.example.hotel.data.repository.bookmark.BookMarkRepository
import com.example.hotel.domain.model.HotelDB

class AddHotel(
    private val repository: BookMarkRepository
) {
    suspend operator fun invoke(hotel: HotelDB) {
        repository.insertHotel(hotel)
    }
}