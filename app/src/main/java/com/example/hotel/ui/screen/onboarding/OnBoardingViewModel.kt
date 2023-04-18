package com.example.hotel.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import com.example.hotel.ui.screen.onboarding.state.OnBoardingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(OnBoardingUiState())
    val state = _state.asStateFlow()
}