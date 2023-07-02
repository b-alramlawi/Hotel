package com.example.hotel.ui.screen.editprofile

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.param.ParamEditProfileDto
import com.example.hotel.data.remote.param.ParamSignUpDto
import com.example.hotel.data.remote.response.dto.auth.UserDto
import com.example.hotel.data.utils.Constants
import com.example.hotel.domain.model.Gender
import com.example.hotel.domain.usecase.auth.EditProfileUseCase
import com.example.hotel.domain.usecase.auth.ProfileUseCase
import com.example.hotel.domain.usecase.auth.UploudUserImageUseCase
import com.example.hotel.ui.screen.editprofile.state.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editProfile: EditProfileUseCase,
    private val uploudImage: UploudUserImageUseCase,
    private val getCurrentUsers: ProfileUseCase,
    private val dataStore: DataStore<Preferences>
): ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = getUserId()
            getCurrentUser(userId!!)
        }
    }

    val genders = arrayListOf(
        Gender(title = "Male", value = 1),
        Gender(title = "Female", value = 2)
    )

    private suspend fun getUserId(): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(Constants.USER_ID)]
    }
    fun onChangeFirstName(newValue: String) {
//        viewModelScope.launch { ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)}
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

    private fun onChangeId(newValue: String) {
        _state.update { it.copy(id = newValue) }
    }

    fun onChangeGender(newValue: String) {
        _state.update { it.copy(gender = newValue) }
    }

    private fun onChangeUser(user: UserDto) {
        _state.update { it.copy(user = user) }
    }

    fun onContinueClick(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            Log.e("samer", "Done")
            try {
                val params = ParamEditProfileDto(
                    firstName = state.value.firstName,
                    lastName = state.value.lastName,
                    phoneNumber = state.value.phoneNumber,
                    gender = state.value.gender,
                    birthDate = state.value.birthdate,
                    id = state.value.id
                )
                val id = editProfile(params)
                Log.e("samer", "Done")
                if (id > 0) {
                    val uri = state.value.profilePicture as Uri
                    val image = getImageReadyToSent(uri, context)
                    val status = uploudImage(image, id.toString())
                    if (status) {
                        onSuccess()
                        Log.e("samer", "Done")
                    }
                }
            } catch (e: IOException) {
                onFailed("Check your internet")
                Log.e("samer", "Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
                Log.e("samer", e.message.toString())
            }
            cancelLoading()
        }
    }

    private fun getCurrentUser(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getCurrentUsers(id)
                onChangeFirstName(response.firstName)
                onChangeLastName(response.lastName)
                onChangeBirthdate(response.birthdate)
                onChangeGender(response.gender)
                onChangePhoneNumber(response.phoneNumber)
                onChangeId(response.id.toString())
                onChangeImage(response.image!!)
                Log.e("samer", response.toString())
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
            }
            cancelLoading()
        }
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

    fun onChangeImage(newValue: Any) {
        _state.update { it.copy(profilePicture = newValue) }
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