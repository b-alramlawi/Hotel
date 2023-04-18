package com.example.hotel.ui.screen.hoteldetails.state

import com.example.hotel.R
import com.example.hotel.domain.model.Facility
import com.example.hotel.domain.model.Review
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

data class HotelDetailsUiState @OptIn(ExperimentalPagerApi::class) constructor(
    val hotelName: String = "Samer Hotel",
    val hotelLocation: String = "Gaza - Palestine",
    val images: ArrayList<String> = arrayListOf(
        "https://media.cntraveler.com/photos/62460965bf4daa9b4dedfed1/master/w_8161,h_6121,c_limit/The%20Tasman,%20a%20Luxury%20Collection%20Hotel_LC_HBALC_Deco_Panoramic_King_CTY_7327-3.jpg",
        "https://media.cntraveler.com/photos/62460965bf4daa9b4dedfed1/master/w_8161,h_6121,c_limit/The%20Tasman,%20a%20Luxury%20Collection%20Hotel_LC_HBALC_Deco_Panoramic_King_CTY_7327-3.jpg",
        "https://media.cntraveler.com/photos/62460965bf4daa9b4dedfed1/master/w_8161,h_6121,c_limit/The%20Tasman,%20a%20Luxury%20Collection%20Hotel_LC_HBALC_Deco_Panoramic_King_CTY_7327-3.jpg",
    ),
    val details: ArrayList<Facility> = arrayListOf(
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
    ),
    val facility: ArrayList<Facility> = arrayListOf(
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
        Facility(image = R.drawable.facility, name = "Hotels"),
    ),
    val review: ArrayList<Review> = arrayListOf(
        Review(image = "https://media.cntraveler.com/photos/62460965bf4daa9b4dedfed1/master/w_8161,h_6121,c_limit/The%20Tasman,%20a%20Luxury%20Collection%20Hotel_LC_HBALC_Deco_Panoramic_King_CTY_7327-3.jpg", name = "Samer Mushtaha", data = "Dec 10, 2024", details = "Very nice and comfortable hotel, thank you for accompanying my vacation!", rate = 5f),
        Review(image = "https://media.cntraveler.com/photos/62460965bf4daa9b4dedfed1/master/w_8161,h_6121,c_limit/The%20Tasman,%20a%20Luxury%20Collection%20Hotel_LC_HBALC_Deco_Panoramic_King_CTY_7327-3.jpg", name = "Samer Mushtaha", data = "Dec 10, 2024", details = "Very nice and comfortable hotel, thank you for accompanying my vacation!", rate = 5f),
        Review(image = "https://media.cntraveler.com/photos/62460965bf4daa9b4dedfed1/master/w_8161,h_6121,c_limit/The%20Tasman,%20a%20Luxury%20Collection%20Hotel_LC_HBALC_Deco_Panoramic_King_CTY_7327-3.jpg", name = "Samer Mushtaha", data = "Dec 10, 2024", details = "Very nice and comfortable hotel, thank you for accompanying my vacation!", rate = 5f),
    ),
    val statePager: PagerState = PagerState(),
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip",
)