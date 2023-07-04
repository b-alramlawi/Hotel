package com.example.hotel.domain.usecase.home

import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.data.repository.home.HomeRepository
import com.example.hotel.domain.model.Tage
import javax.inject.Inject

class HotelsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String): List<Hotel> {
        val images = arrayListOf(
            "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
            "https://media.istockphoto.com/id/119926339/photo/resort-swimming-pool.jpg?s=612x612&w=0&k=20&c=9QtwJC2boq3GFHaeDsKytF4-CavYKQuy1jBD2IRfYKc=",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFvFqvCMljoJh3X915WU4kDZ5TqQ3PaNpoCEuqzIck7w&s",
            "https://www.engelvoelkers.com/images/07e43544-9e5f-4571-ae73-7170dc2d6809/modern-villa-project-with-innovative-concept",
            "https://images.unsplash.com/photo-1580587771525-78b9dba3b914?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dmlsbGF8ZW58MHx8MHx8fDA%3D&w=1000&q=80"
        )
        val response = repository.getHotelsByTags(id)
        for (i in 0 until response.data!!.hotels.size) {
            if(response.data.hotels[i].name == "Mushtaha"){
                response.data.hotels[i].images = images[0]
            }else{
                response.data.hotels[i].images = images[i]
            }
        }
        return response.data.hotels
    }
}