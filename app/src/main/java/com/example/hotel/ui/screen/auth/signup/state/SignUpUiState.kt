package com.example.hotel.ui.screen.auth.signup.state

data class SignUpUiState (
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
)