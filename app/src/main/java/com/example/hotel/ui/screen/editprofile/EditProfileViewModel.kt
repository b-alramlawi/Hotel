package com.example.hotel.ui.screen.editprofile

import androidx.lifecycle.ViewModel
import com.example.hotel.domain.model.Gender
import com.example.hotel.ui.screen.editprofile.state.EditProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState())
    val state = _state.asStateFlow()

    val genders = arrayListOf(
        Gender(title = "Male", value = 1),
        Gender(title = "Female", value = 2)
    )

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

    fun onChangeGender(newValue: String) {
        _state.update { it.copy(gender = newValue) }
    }
}