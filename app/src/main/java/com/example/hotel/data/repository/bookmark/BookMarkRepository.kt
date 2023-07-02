package com.example.hotel.data.repository.bookmark

import com.example.hotel.domain.model.HotelDB
import kotlinx.coroutines.flow.Flow

interface BookMarkRepository {

    fun getHotels(): List<HotelDB>

    suspend fun insertHotel(hotel: HotelDB)

    suspend fun deleteHotel(hotel: HotelDB)
}