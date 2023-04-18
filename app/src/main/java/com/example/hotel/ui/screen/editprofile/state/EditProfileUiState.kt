package com.example.hotel.ui.screen.editprofile.state

data class EditProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    var birthdate: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
)