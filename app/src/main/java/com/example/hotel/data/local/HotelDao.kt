package com.example.hotel.data.local

import androidx.room.*
import com.example.hotel.domain.model.HotelDB
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Query("SELECT * FROM hoteldb")
    fun getHotels(): List<HotelDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(note: HotelDB)

    @Delete
    suspend fun deleteHotel(note: HotelDB)
}