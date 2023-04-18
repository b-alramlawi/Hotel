package com.example.hotel.ui.screen.auth.setupprofile.state

import com.example.hotel.R
import okhttp3.MultipartBody

data class SetUpProfileUiState(
 val firstName: String = "",
 val lastName: String = "",
 var birthdate: String = "",
 val phoneNumber: String = "",
 val profilePicture: Any = "https://icon-library.com/images/icon-of-a-person/icon-of-a-person-7.jpg",
 val gender: String = "",
 val btnTitle:Int = R.string.contenue,
 val btnEnable: Boolean = false,
 val isLoading: Boolean = false,
 val isSuccess: Boolean = false,
 val isFailed: Boolean = false,
 val errorMessage: String = "",
 val image: MultipartBody.Part? = null
)