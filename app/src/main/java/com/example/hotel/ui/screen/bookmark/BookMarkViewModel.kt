package com.example.hotel.ui.screen.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotel.domain.model.HotelDB
import com.example.hotel.domain.usecase.bookmark.BookMarkUseCases
import com.example.hotel.ui.screen.bookmark.state.BookMarkUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val noteUseCases: BookMarkUseCases,
): ViewModel() {

    private val _state = MutableStateFlow(BookMarkUiState())
    val state = _state.asStateFlow()

    init {
        getAllBookMark()
    }
    private fun addBookMark(hotel: HotelDB){
        viewModelScope.launch {
            try {
                noteUseCases.addHotel(hotel)
            } catch(e: Exception) {
                onFailed("Something went wrong")
            }
        }
    }

    private fun deleteBookMark(hotel: HotelDB){
        viewModelScope.launch {
            try {
                noteUseCases.deleteHotel(hotel)
            } catch(e: Exception) {
                onFailed("Something went wrong")
            }
        }
    }

    fun getAllBookMark(){
        viewModelScope.launch {
            try {
                val list = noteUseCases.getHotels()
                onChangeBookMarks(list)
            } catch(e: Exception) {
                onFailed("Something went wrong")
                Log.e("samer", e.message.toString())
            }
        }

    }

    fun onBookMarkClick(hotel: HotelDB){
        viewModelScope.launch {
            if(state.value.bookMarks.contains(hotel)){
                deleteBookMark(hotel)
                val index = state.value.bookMarks.indexOf(hotel)
                state.value.bookMarks.drop(index)
            }else{
                addBookMark(hotel)
                state.value.bookMarks.plus(hotel)
            }
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


    private fun onChangeBookMarks(newValue: List<HotelDB>) {
        _state.update { it.copy(bookMarks = newValue) }
    }

}