package com.example.hotel.ui.screen.auth.forgotpassword.state

data class ForgotPasswordUiState(
 val verificationCode: String = "",
 val isLoading: Boolean = false,
 val isSuccess: Boolean = false,
 val isFailed: Boolean = false,
 val errorMessage: String = "",
)