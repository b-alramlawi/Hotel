package com.example.hotel.data.repository.booking

import com.example.hotel.data.remote.param.ParamBookingDto
import com.example.hotel.data.remote.response.BaseResponse
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.Booking
import com.example.hotel.data.remote.response.dto.home.BookingStatus
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.remote.response.dto.home.HotelDetailsDto
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.remote.response.dto.home.HotelX

interface BookingRepository {
    suspend fun bookHotel(params: ParamBookingDto): BaseResponse<Booking>
    suspend fun getBookingByStatus(status: String): BaseResponse<ArrayList<BookingStatus>>
}