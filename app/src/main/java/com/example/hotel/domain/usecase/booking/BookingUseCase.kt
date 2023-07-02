package com.example.hotel.domain.usecase.booking

import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.BookingStatus
import com.example.hotel.data.repository.booking.BookingRepository
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.domain.model.Tage
import javax.inject.Inject

class BookingUseCase@Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(status: String): ArrayList<BookingStatus> {
        val response = repository.getBookingByStatus(status)
        return response.data!!
    }
}