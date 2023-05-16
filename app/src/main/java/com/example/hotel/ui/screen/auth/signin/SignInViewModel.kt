package com.example.hotel.ui.screen.auth.signin

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.auth.signin.state.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(SignInUiState())
    val state = _state.asStateFlow()

    fun onChangeEmail(newValue: String) {
        _state.update { it.copy(email = newValue) }
    }

    fun onChangePassword(newValue: String) {
        _state.update { it.copy(password = newValue) }
    }

    fun onChangeRememberCheck(newValue: Boolean) {
        _state.update { it.copy(rememberMe = newValue) }
    }

    fun isSignInEnable(): Boolean {
        return (_state.value.password.length >= 8) && (_state.value.email.contains("@"))
    }

    fun onGoogleClick(){

    }

    fun onDismissRequest() {
        _state.update { it.copy(dialogShowed = !_state.value.dialogShowed) }
    }

    fun onForgetPasswordClick() {
        if(_state.value.email.contains("@")){
            onDismissRequest()
        }
    }

}