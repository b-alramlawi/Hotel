package com.example.hotel.ui.screen.auth.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.domain.usecase.auth.SignUpUseCase
import com.example.hotel.domain.usecase.auth.VerifyCodeUseCase
import com.example.hotel.ui.screen.auth.forgotpassword.state.ForgotPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val verifyCode: VerifyCodeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ForgotPasswordUiState())
    val state = _state.asStateFlow()

    fun onChangeVerificationCode(newValue: String) {
        _state.update { it.copy(verificationCode = newValue) }
    }

    fun onContinueClick(email :String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val params = ParamVerifyCodeDto(
                    email = email,
                    verifyCode = state.value.verificationCode
                )
                val response = verifyCode(params)
                if (response) {
                    onSuccess()
                }
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Verify Code was Failed")
            }
            cancelLoading()
        }
    }

    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun setErrorMessage(message: String) {
        _state.update { it.copy(errorMessage = message) }
    }

    private fun cancelLoading() {
        _state.update { it.copy(isLoading = false) }
    }

    private fun onSuccess() {
        _state.update { it.copy(isSuccess = true) }
    }

    private fun onFailed(message: String) {
        setErrorMessage(message)
        _state.update { it.copy(isFailed = true) }
    }

    fun clearErrorMessage() {
        _state.update { it.copy(errorMessage = "") }
    }

    fun isContinueButtonEnable(): Boolean {
        return (_state.value.verificationCode.length == 4)
    }
}