package com.example.hotel.ui.screen.profile.state

import com.example.hotel.data.remote.response.dto.auth.UserDto

data class ProfileUiState(
    val name: String = "",
    val email: String = "",
    val image: Any = "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
    val user: UserDto? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
)