package com.example.hotel.ui.screen.onboarding.state

import com.example.hotel.R
import com.example.hotel.domain.model.Page

data class OnBoardingUiState(
    val pages: ArrayList<Page> = arrayListOf(
        Page(
            image = R.drawable.onboarding_1,
            title = R.string.on_boarding_1_title,
            subtitle = R.string.on_boarding_1_subtitle
        ),
        Page(
            image = R.drawable.onboarding_2,
            title = R.string.on_boarding_2_title,
            subtitle = R.string.on_boarding_2_subtitle
        ),
        Page(
            image = R.drawable.onboarding_3,
            title = R.string.on_boarding_3_title,
            subtitle = R.string.on_boarding_3_subtitle
        )
    )
)