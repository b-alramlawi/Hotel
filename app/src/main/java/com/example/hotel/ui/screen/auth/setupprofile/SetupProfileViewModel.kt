package com.example.hotel.ui.screen.auth.setupprofile

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.domain.model.Gender
import com.example.hotel.domain.usecase.auth.SignUpUseCase
import com.example.hotel.domain.usecase.auth.UploudImageUseCase
import com.example.hotel.ui.screen.auth.setupprofile.state.SetUpProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.parse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SetupProfileViewModel @Inject constructor(
    private val signUp: SignUpUseCase,
    private val uploudImage: UploudImageUseCase,
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

    fun onContinueClick(email: String, password: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val params = ParamSignUpDto(
                    firstName = state.value.firstName,
                    lastName = state.value.lastName,
                    email = email,
                    phoneNumber = state.value.phoneNumber,
                    gender = state.value.gender,
                    birthDate = state.value.birthdate,
                    password = password
                )
                val id = signUp(params)
                if (id > 0) {
                    val uri = state.value.profilePicture as Uri
                    val image = getImageReadyToSent(uri, context)
                    val status = uploudImage(image, id.toString())
                    if (status) {
                        onSuccess()
                    }
                }
            } catch (e: IOException) {
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
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
        return (
                _state.value.firstName.isNotBlank() &&
                        _state.value.lastName.isNotBlank() &&
                        _state.value.birthdate.isNotBlank() &&
                        _state.value.gender.isNotBlank() &&
                        _state.value.phoneNumber.isNotBlank() &&
                        _state.value.profilePicture.toString().isNotBlank()
                )
    }

    private fun getImageReadyToSent(uri: Uri, context: Context): MultipartBody.Part {
        var path = ""
        val contentResolver = context.contentResolver
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                path = it.getString(columnIndex)
            }
        }
        val file = File(path)
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}