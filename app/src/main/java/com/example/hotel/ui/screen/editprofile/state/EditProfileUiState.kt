package com.example.hotel.ui.screen.editprofile.state

import com.example.hotel.data.remote.response.dto.auth.UserDto

data class EditProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    var birthdate: String = "",
    var id: String = "",
    val phoneNumber: String = "",
    val gender: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
    val user: UserDto? = null,
    val profilePicture: Any = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
)