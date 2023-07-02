package com.example.hotel.di

import android.app.Application
import androidx.room.Room
import com.example.hotel.data.local.HotelDatabase
import com.example.hotel.data.repository.bookmark.BookMarkRepository
import com.example.hotel.domain.usecase.bookmark.AddHotel
import com.example.hotel.domain.usecase.bookmark.BookMarkUseCases
import com.example.hotel.domain.usecase.bookmark.DeleteHotel
import com.example.hotel.domain.usecase.bookmark.GetHotels
import com.example.hotel.domain.usecase.home.HotelUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHotelDatabase(app: Application): HotelDatabase {
        return Room.databaseBuilder(
            app,
            HotelDatabase::class.java,
            HotelDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: BookMarkRepository): BookMarkUseCases {
        return BookMarkUseCases(
            getHotels = GetHotels(repository),
            deleteHotel = DeleteHotel(repository),
            addHotel = AddHotel(repository),
        )
    }
}