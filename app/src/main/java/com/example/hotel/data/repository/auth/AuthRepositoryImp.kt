package com.example.hotel.data.repository.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.service.LuxeStayApi
import com.example.hotel.data.utils.Constants.TOKEN
import com.example.hotel.di.DataStoreModule
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val api: LuxeStayApi,
    private val dataStore: DataStore<Preferences>
) : AuthRepository {

    override suspend fun signup(params: ParamSignUpDto): BaseResponse<SignUpDto> {
        return api.signup(
            firstName = params.firstName,
            lastName = params.lastName,
            birthdate = params.birthDate,
            phoneNumber = params.phoneNumber,
            gender = params.gender,
            email = params.email,
            password = params.password,
            image = params.image
        )
    }

    override suspend fun storeToken(token: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(TOKEN)] = token
        }
    }

}