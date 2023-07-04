package com.example.hotel.ui.screen.profile

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.data.remote.response.dto.auth.UserDto
import com.example.hotel.data.utils.Constants.USER_ID
import com.example.hotel.domain.usecase.auth.ProfileUseCase
import com.example.hotel.domain.usecase.home.HotelsDetailsUseCase
import com.example.hotel.ui.screen.profile.state.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUsers: ProfileUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = getUserId()
            Log.e("samer", userId.toString())
            getCurrentUser(userId!!)
        }
    }
    fun onChangeImage(newValue: Any) {
        _state.update { it.copy(image = newValue) }
    }

    fun getCurrentUser(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getCurrentUsers(id)
                onChangeUser(response)
                Log.e("samer", response.toString())
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
            }
            cancelLoading()
        }
    }

    suspend fun getUserId(): String? {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(USER_ID)]
    }

    private fun startLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun setErrorMessage(message: String) {
        _state.update { it.copy(errorMessage = message) }
    }

    private fun onChangeUser(user: UserDto) {
        _state.update { it.copy(user = user) }
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

}