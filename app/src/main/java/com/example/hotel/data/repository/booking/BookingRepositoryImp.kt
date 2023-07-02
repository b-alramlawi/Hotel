package com.example.hotel.data.repository.booking

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.hotel.data.remote.param.ParamBookingDto
import com.example.hotel.data.remote.param.ParamResetPasswordDto
import com.example.hotel.data.remote.param.ParamSignInDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.SignInDto
import com.example.hotel.data.remote.response.dto.auth.SignUpDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.Booking
import com.example.hotel.data.remote.response.dto.home.BookingStatus
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

class BookingRepositoryImp @Inject constructor(
    private val api: LuxeStayApi
) : BookingRepository {
    override suspend fun bookHotel(params: ParamBookingDto): BaseResponse<Booking> {
        return api.bookHotel(
            userId = params.userId,
            hotelId = params.hotelId,
            checkInDate = params.checkInDate,
            checkOutDate = params.checkOutDate,
            guestCount = params.guestCount,
            roomRatePerNight = params.price,
            paymentMethod = params.paymentMethod
        )
    }

    override suspend fun getBookingByStatus(status: String): BaseResponse<ArrayList<BookingStatus>> {
        return api.getBookingByStatus(status)
    }

}