package com.example.hotel.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.hotel.data.local.HotelDatabase
import com.example.hotel.data.remote.service.LuxeStayApi
import com.example.hotel.data.repository.auth.AuthRepository
import com.example.hotel.data.repository.auth.AuthRepositoryImp
import com.example.hotel.data.repository.booking.BookingRepository
import com.example.hotel.data.repository.booking.BookingRepositoryImp
import com.example.hotel.data.repository.bookmark.BookMarkRepository
import com.example.hotel.data.repository.bookmark.BookMarkRepositoryImpl
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.data.repository.home.HomeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: LuxeStayApi, dataStore: DataStore<Preferences>): AuthRepository {
        return AuthRepositoryImp(api, dataStore)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(api: LuxeStayApi): HomeRepository {
        return HomeRepositoryImp(api)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: HotelDatabase): BookMarkRepository {
        return BookMarkRepositoryImpl(db.hotelDao)
    }

    @Provides
    @Singleton
    fun provideBookingRepository(api: LuxeStayApi): BookingRepository {
        return BookingRepositoryImp(api)
    }
}