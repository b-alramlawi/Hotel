package com.example.hotel.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.request.Tags
import com.example.hotel.data.remote.param.ParamVerifyCodeDto
import com.example.hotel.data.remote.response.dto.auth.TagDto
import com.example.hotel.data.remote.response.dto.home.Hotel
import com.example.hotel.data.remote.response.dto.home.HotelDto
import com.example.hotel.domain.model.HotelDB
import com.example.hotel.domain.usecase.auth.VerifyCodeUseCase
import com.example.hotel.domain.usecase.bookmark.BookMarkUseCases
import com.example.hotel.domain.usecase.home.HotelUseCase
import com.example.hotel.domain.usecase.home.HotelsDetailsUseCase
import com.example.hotel.domain.usecase.home.HotelsUseCase
import com.example.hotel.domain.usecase.home.TagsUseCase
import com.example.hotel.ui.screen.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllHotelTags: TagsUseCase,
    private val getHotelsByTags: HotelsUseCase,
    private val getHotelDetails: HotelsDetailsUseCase,
    private val getAllHotel: HotelUseCase,
    private val noteUseCases: BookMarkUseCases,
): ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllTags()
            getAllHotels()
        }
        getAllBookMark()
    }

    fun onChangeSearch(newValue: String) {
        _state.update { it.copy(search = newValue) }
    }

    fun onChangeIsSave() {
        _state.update { it.copy(isSave = !state.value.isSave) }
    }

    fun onSelectedChange(newValue: TagDto) {
        _state.update { it.copy(selectedChip = newValue) }
        getHotelsByTag(_state.value.selectedChip!!.id)
    }

    private fun getAllTags() {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getAllHotelTags()
                onChangeTags(response)
                onSelectedChange(state.value.chips[0])
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
            }
            cancelLoading()
        }
    }

    private fun getAllHotels() {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getAllHotel()
                onChangeHotel(response)
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
                Log.e("samer", e.message.toString())
            }
            cancelLoading()
        }
    }

    private fun getHotelsByTag(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            startLoading()
            try {
                val response = getHotelsByTags(id)
                onChangeHotels(response)
            } catch (e: IOException){
                onFailed("Check your internet")
            } catch (e: Exception) {
                onFailed("Something went wrong")
                Log.e("samer", e.message.toString())
            }
            cancelLoading()
        }
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

            for (i in state.value.bookMarks){
                Log.e("samer", i.toString())
                if(i.hotel_id == hotel.hotel_id){
                    Log.e("samer", "Done")
                    val hotelDB = HotelDB(
                        id = i.id,
                        hotel_id = i.hotel_id,
                        name = i.name,
                        image = i.image,
                        rate = i.rate,
                        location = i.location,
                        price = i.price
                    )
                    Log.e("samer", hotelDB.toString())
                    deleteBookMark(hotelDB)
                    getAllBookMark()
//                    val index = state.value.bookMarks.indexOf(hotelDB)
//                    state.value.bookMarks.drop(index)
                    return
                }
            }
            addBookMark(hotel)
            getAllBookMark()
//            state.value.bookMarks.plus(hotel)
//            Log.e("samer", hotel.toString())

    }

    fun isSave(hotel: HotelDB): Boolean{
        for (i in state.value.bookMarks){
            Log.e("samer", i.hotel_id + " " + hotel.hotel_id)
            if(i.hotel_id == hotel.hotel_id){
                return true
            }
        }
        return false
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

    private fun onChangeTags(newValue: ArrayList<TagDto>) {
        _state.update { it.copy(chips = newValue) }
    }

    private fun onChangeBookMarks(newValue: List<HotelDB>) {
        _state.update { it.copy(bookMarks = newValue) }
    }

    private fun onChangeHotels(newValue: List<Hotel>) {
        _state.update { it.copy(hotels = newValue) }
    }
    private fun onChangeHotel(newValue: List<Hotel>) {
        _state.update { it.copy(hotels2 = newValue) }
    }
}