package com.example.hotel.ui.screen.auth.signin.state

data class SignInUiState (
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
)