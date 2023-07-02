package com.example.hotel.domain.usecase.booking

import com.example.hotel.data.remote.param.ParamBookingDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.repository.auth.AuthRepository
import com.example.hotel.data.repository.booking.BookingRepository
import javax.inject.Inject

class AddBookingUseCase@Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(params: ParamBookingDto): Boolean {
        val response = repository.bookHotel(params)
        return response.status == "true"
    }
}

