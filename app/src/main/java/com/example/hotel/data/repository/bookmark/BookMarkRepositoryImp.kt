package com.example.hotel.data.repository.bookmark

import com.example.hotel.data.local.HotelDao
import com.example.hotel.domain.model.HotelDB
import kotlinx.coroutines.flow.Flow

class BookMarkRepositoryImpl(
    private val dao: HotelDao
) : BookMarkRepository {

    override fun getHotels(): List<HotelDB> {
        return dao.getHotels()
    }

    override suspend fun insertHotel(hotel: HotelDB) {
        dao.insertHotel(hotel)
    }

    override suspend fun deleteHotel(hotel: HotelDB) {
        dao.deleteHotel(hotel)
    }
}