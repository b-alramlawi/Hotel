package com.example.hotel.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.hotel.data.remote.service.LuxeStayApi
import com.example.hotel.data.repository.auth.AuthRepository
import com.example.hotel.data.repository.auth.AuthRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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
}