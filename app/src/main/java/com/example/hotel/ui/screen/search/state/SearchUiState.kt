package com.example.hotel.ui.screen.search.state


data class SearchUiState(
    val search: String = "",
    val recentlyList: ArrayList<String> = arrayListOf(
        "Palazzo Hotel",
        "Palazzo Hotel",
        "Palazzo Hotel",
    ),
    val countries: ArrayList<String> = arrayListOf(
        "France",
        "France",
        "France",
    ),
    val selectedCountry: String = "",
    val sortTypes: ArrayList<String> = arrayListOf(
        "Highest Price",
        "Highest Price",
        "Highest Price",
    ),
    val selectedSort: String = "",
    val ratings: ArrayList<String> = arrayListOf(
        "5",
        "5",
        "5",
    ),
    val selectedRate: String = "",
    val facilities: ArrayList<String> = arrayListOf(
        "WiFi",
        "WiFi",
        "WiFi",
    ),
    val selectedFacility: ArrayList<String> = arrayListOf()
)