package com.example.hotel.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.hotel.ui.theme.*
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerificationCode(
    modifier: Modifier = Modifier,
    value: String,
    otpCount: Int = 4,
    onValueChange: (String) -> Unit
) {
    var isFocus by remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(
        modifier = modifier
            .onFocusEvent { focusState ->
                isFocus = focusState.isFocused
                if(value.length == 4){
                    keyboardController!!.hide()
                }
            },
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                repeat(otpCount) { index ->
                    CharView(
                        index = index,
                        text = value,
                        isFocus = isFocus
                    )
                }
            }
        }
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String,
    isFocus: Boolean
) {
    val isFocused = text.length == index && isFocus
    val char = when {
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }

    Text(
        modifier = Modifier
            .width(70.dp)
            .height(60.dp)
            .border(
                1.dp, when {
                    isFocused -> MaterialTheme.colors.primary
                    else -> MaterialTheme.colors.onSecondary
                }, RoundedCornerShape(12.dp)
            )
            .background(
                color = if (isFocused) {
                    MaterialTheme.colors.primary.copy(alpha = 0.1f)
                } else {
                    MaterialTheme.colors.onSecondary
                },
                shape = RoundedCornerShape(12.dp)
            )
            .padding(15.dp),
        text = char,
        style = MaterialTheme.typography.h4.copy(),
        color = if (isFocused) MaterialTheme.colors.primary else MaterialTheme.colors.textPrimaryColor,
        textAlign = TextAlign.Center
    )
}