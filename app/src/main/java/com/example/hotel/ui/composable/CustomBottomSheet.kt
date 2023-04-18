package com.example.hotel.ui.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import com.example.hotel.ui.theme.Blake800
import com.example.hotel.ui.theme.roundedLarge

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomBottomSheet(modalSheetState: ModalBottomSheetState, sheetContent: @Composable () -> Unit, screenContent: @Composable () -> Unit){
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topEnd = roundedLarge, topStart = roundedLarge),
        scrimColor = Blake800.copy(alpha = 0.5f),
        sheetContent = { sheetContent() },
        content = screenContent
    )
}