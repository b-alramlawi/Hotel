package com.example.hotel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hotel.domain.model.HotelDB

@Database(
    entities = [HotelDB::class],
    version = 3
)
abstract class HotelDatabase : RoomDatabase() {

    abstract val hotelDao: HotelDao

    companion object {
        const val DATABASE_NAME = "hotel_db"
    }
}