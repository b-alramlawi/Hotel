package com.example.hotel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.domain.usecase.auth.VerifyCodeUseCase
import com.example.hotel.domain.usecase.home.TagsUseCase
import com.example.hotel.ui.screen.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllHotelTags: TagsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
//        getAllTags()
    }

    fun onChangeSearch(newValue: String) {
        _state.update { it.copy(search = newValue) }
    }

    fun onSelectedChange(newValue: TagDto) {
        _state.update { it.copy(selectedChip = newValue) }
    }

    private fun getAllTags() {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getAllHotelTags()
                onChangeTags(response)
            } catch (e: IOException){
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

    fun onChangeTags(newValue: ArrayList<TagDto>) {
        _state.update { it.copy(chips = newValue) }
    }
}