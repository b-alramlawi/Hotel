package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.hotel.R
import com.example.hotel.ui.theme.spacingSmall

@Composable
fun CustomSnackBar(message: String) {
    Snackbar(
//        action = {
//            Button(onClick = { onClickAction() }) {
//                Text(stringResource(R.string.ok))
//            }
//        },
        modifier = Modifier.padding(spacingSmall)
    ) {
        Text(
            text = message,
        )
    }
}