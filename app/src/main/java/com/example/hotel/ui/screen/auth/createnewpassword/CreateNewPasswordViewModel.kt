package com.example.hotel.ui.screen.auth.createnewpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamResetPasswordDto
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.domain.usecase.auth.ResetPasswordUseCase
import com.example.hotel.ui.screen.auth.createnewpassword.state.CreateNewPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CreateNewPasswordViewModel @Inject constructor(
    private val resetPassword: ResetPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CreateNewPasswordUiState())
    val state = _state.asStateFlow()

    fun onChangeNewPassword(newValue: String) {
        _state.update { it.copy(newPassword = newValue) }
    }

    fun onChangeConfirmPassword(newValue: String) {
        _state.update { it.copy(confirmPassword = newValue) }
    }

    fun onChangeRemember(newValue: Boolean) {
        _state.update { it.copy(rememberMe = newValue) }
    }

    fun onBackClick() {

    }

    fun onGoToLoginClick() {
        onDismissRequest()
    }

    fun onDismissRequest() {
        _state.update { it.copy(dialogShowed = !_state.value.dialogShowed) }
    }

    fun onContinueClick(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val params = ParamResetPasswordDto(
                    email = email,
                    password = state.value.newPassword
                )
                val response = resetPassword(params)
                if (response) {
                    onDismissRequest()
                }
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something was wrong")
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

    fun isContenueEnable(): Boolean {
        return (_state.value.newPassword.length >= 8) && (_state.value.confirmPassword.length >= 8) && (_state.value.newPassword == _state.value.confirmPassword)
    }

}