package com.example.hotel.ui.screen.auth.createnewpassword

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.auth.createnewpassword.state.CreateNewPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateNewPasswordViewModel @Inject constructor(): ViewModel() {

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

    fun onContinueClick() {
        _state.update { it.copy(dialogShowed = !_state.value.dialogShowed) }
    }

    fun isSignUpEnable(): Boolean {
        return (_state.value.newPassword.length >= 8) && (_state.value.confirmPassword.length >= 8) && (_state.value.newPassword == _state.value.confirmPassword)
    }

}