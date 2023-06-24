package com.example.hotel.ui.screen.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamSignInDto
import com.example.hotel.domain.usecase.auth.ForgotPasswordUseCase
import com.example.hotel.domain.usecase.auth.SignInUseCase
import com.example.hotel.ui.screen.auth.signin.state.SignInUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signIn: SignInUseCase,
    private val forgotPassword: ForgotPasswordUseCase
): ViewModel() {

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

    fun onDismissRequest() {
        _state.update { it.copy(dialogShowed = !_state.value.dialogShowed) }
    }

    fun onForgetPasswordClick() {
        if(_state.value.email.contains("@")){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = forgotPassword(state.value.email)
                    if(response){
                        onDismissRequest()
                    }
                } catch (e: IOException){
                    onFailed("Check your internet")
                } catch (e: Exception) {
                    onFailed("Unable to send email")
                }
            }
        }
    }

    fun onSignInClick(){
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val params = ParamSignInDto(
                    email = state.value.email,
                    password = state.value.password
                )
                val response = signIn(params)
                if(response){
                    onSuccess()
                }
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                if(e.message == "HTTP 401 Unauthorized"){
                    onFailed("Email or Password was wrong")
                }else{
                    onFailed("Something went wrong")
                }
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

    fun clearErrorMessage() {
        _state.update { it.copy(errorMessage = "") }
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

}