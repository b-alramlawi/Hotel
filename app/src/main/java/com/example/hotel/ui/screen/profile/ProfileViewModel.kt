package com.example.hotel.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.profile.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {
    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    fun onChangeImage(newValue: Any) {
        _state.update { it.copy(image = newValue) }
    }

}