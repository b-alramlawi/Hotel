package com.example.hotel.ui.screen.auth.signin.state

data class SignInUiState (
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val dialogShowed: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val errorMessage: String = "",
)