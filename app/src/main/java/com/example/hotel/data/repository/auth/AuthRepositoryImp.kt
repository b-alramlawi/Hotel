package com.example.hotel.data.repository.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hotel.data.remote.param.ParamEditProfileDto
import com.example.hotel.data.remote.param.ParamResetPasswordDto
import com.example.hotel.data.remote.param.ParamSignInDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.response.dto.auth.UserDto
import com.example.hotel.data.remote.service.LuxeStayApi
import com.example.hotel.data.utils.Constants.TOKEN
import com.example.hotel.data.utils.Constants.USER_ID
import com.example.hotel.di.DataStoreModule
import okhttp3.MultipartBody
import retrofit2.Response
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
//            image = params.image
        )
    }

    override suspend fun signIn(params: ParamSignInDto): BaseResponse<SignInDto> {
        return api.signIn(
            email = params.email,
            password = params.password
        )
    }

    override suspend fun storeToken(token: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(TOKEN)] = token
        }
    }

    override suspend fun storeUserId(id: String) {
        dataStore.edit { MutableStringPref ->
            MutableStringPref[stringPreferencesKey(USER_ID)] = id
        }
    }

    override suspend fun forgotPassword(email: String): BaseResponse<Any?> {
        return api.forgotPassword(email)
    }

    override suspend fun verifyCode(params: ParamVerifyCodeDto): BaseResponse<Any?> {
        return api.verifyCode(email = params.email, verifyCode = params.verifyCode)
    }

    override suspend fun resetPassword(params: ParamResetPasswordDto): BaseResponse<Any?> {
        return api.resetPassword(email = params.email, verifyCode = params.password)
    }

    override suspend fun uploadUserImage(file: MultipartBody.Part, userId: String): BaseResponse<Any?> {
        return api.uploadUserImage(file, userId)
    }

    override suspend fun getCurrentUser(id: String): BaseResponse<UserDto> {
        return api.getCurrentUser(id)
    }

    override suspend fun editProfile(params: ParamEditProfileDto): BaseResponse<UserDto> {
        return api.editProfile(
            firstName = params.firstName,
            lastName = params.lastName,
            birthdate = params.birthDate,
            phoneNumber = params.phoneNumber,
            gender = params.gender,
            id = params.id
        )
    }

    override suspend fun updateUserImage(
        file: MultipartBody.Part,
        userId: String
    ): BaseResponse<Any?> {
        return api.updateUserImage(file, userId)
    }

}