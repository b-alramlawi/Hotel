package com.example.hotel.domain.model

data class TopBarItem(
    val title: String,
    val actions: ArrayList<Action>
)

data class Action(
    val icon: Int,
    val onClick: () -> Unit
)
