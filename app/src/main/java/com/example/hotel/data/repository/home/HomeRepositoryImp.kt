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
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.remote.response.dto.home.HotelDetailsDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.remote.response.dto.home.HotelX
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

    override suspend fun getAllHotels(): BaseResponse<ArrayList<Hotel>> {
        return api.getAllHotels()
    }

    override suspend fun getHotelsByTags(id: String): BaseResponse<HotelDto> {
        return api.getHotelByTage(id)
    }

    override suspend fun getHotelDetails(id: String): BaseResponse<HotelX> {
        return api.getHotelDetails(id)
    }
}