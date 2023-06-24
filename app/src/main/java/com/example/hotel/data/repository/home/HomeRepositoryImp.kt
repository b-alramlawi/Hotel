package com.example.hotel.data.repository.home

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hotel.data.remote.param.ParamResetPasswordDto
import com.example.hotel.data.remote.param.ParamSignInDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.service.LuxeStayApi
import com.example.hotel.data.utils.Constants.TOKEN
import com.example.hotel.di.DataStoreModule
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val api: LuxeStayApi
) : HomeRepository {
    override suspend fun getAllTags(): BaseResponse<ArrayList<TagDto>> {
        return api.getHotelTags()
    }
}