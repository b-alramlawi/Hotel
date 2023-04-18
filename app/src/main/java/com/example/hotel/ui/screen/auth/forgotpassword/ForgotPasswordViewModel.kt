package com.example.hotel.ui.screen.auth.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.ui.screen.auth.forgotpassword.state.ForgotPasswordUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(ForgotPasswordUiState())
    val state = _state.asStateFlow()

    init {
        timer()
    }

    private fun timer(){
        viewModelScope.launch(Dispatchers.IO){
            while(_state.value.seconds > 0) {
                delay(1000)
                _state.update { it.copy(seconds = _state.value.seconds - 1) }
            }
        }
    }

    fun onChangeVerificationCode(newValue: String) {
        _state.update { it.copy(verificationValues = newValue.take(4)) }
    }

    fun onBackClick(){

    }

    fun onResendCodeClick(){

    }
}