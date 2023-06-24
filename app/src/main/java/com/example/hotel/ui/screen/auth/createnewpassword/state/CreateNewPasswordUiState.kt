package com.example.hotel.ui.screen.auth.createnewpassword.state

data class CreateNewPasswordUiState(
    val newPassword: String = "",
    val confirmPassword: String = "",
    val rememberMe: Boolean = false,
    val dialogShowed: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
)