package com.example.hotel.ui.screen.auth.setupprofile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.domain.model.Gender
import com.example.hotel.domain.usecase.auth.SignUpUseCase
import com.example.hotel.ui.screen.auth.setupprofile.state.SetUpProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class SetupProfileViewModel @Inject constructor(
    private val signUp: SignUpUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SetUpProfileUiState())
    val state = _state.asStateFlow()

    val genders = arrayListOf(
        Gender(title = "Male", value = 1),
        Gender(title = "Female", value = 2)
    )

    fun onChangeFirstName(newValue: String) {
        _state.update { it.copy(firstName = newValue) }
    }

    fun onChangeLastName(newValue: String) {
        _state.update { it.copy(lastName = newValue) }
    }

    fun onChangeBirthdate(newValue: String) {
        _state.update { it.copy(birthdate = newValue) }
    }

    fun onChangePhoneNumber(newValue: String) {
        _state.update { it.copy(phoneNumber = newValue) }
    }

    fun onChangeGender(newValue: String) {
        _state.update { it.copy(gender = newValue) }
    }

    fun onChangeImage(newValue: Any) {
        _state.update { it.copy(profilePicture = newValue) }
    }

    fun onChangeFile(newValue: MultipartBody.Part) {
        _state.update { it.copy(image = newValue) }
    }

    fun onBackClick() {

    }

    fun onContinueClick(email: String, password: String) {
        startLoading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = signUp(
                    ParamSignUpDto(
                        firstName = state.value.firstName,
                        lastName = state.value.lastName,
                        email = email,
                        phoneNumber = state.value.phoneNumber,
                        gender = state.value.gender,
                        birthDate = state.value.birthdate,
                        image = state.value.image!!,
                        password = password
                    )
                )
                if (response.status == "true") {
                    cancelLoading()
                    onSuccess()
                } else {
                    cancelLoading()
                    onFailed()
                    setErrorMessage(response.message!!)
                }
            } catch (e: Exception) {
                cancelLoading()
                onFailed()
                setErrorMessage(e.message.toString())
                Log.e("samer", e.message.toString())
            }
        }
    }

    fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    fun setErrorMessage(message: String) {
        _state.update { it.copy(errorMessage = message) }
    }

    fun cancelLoading() {
        _state.update { it.copy(isLoading = false) }
    }

    fun onSuccess() {
        _state.update { it.copy(isSuccess = true) }
    }

    fun onFailed() {
        _state.update { it.copy(isFailed = true) }
    }

    fun isContinueButtonEnable(): Boolean {
        return (
                _state.value.firstName.isNotBlank() &&
                        _state.value.lastName.isNotBlank() &&
                        _state.value.birthdate.isNotBlank() &&
                        _state.value.gender.isNotBlank() &&
                        _state.value.phoneNumber.isNotBlank()
                )
    }
}