package com.example.hotel.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.Gray500
import com.example.hotel.ui.theme.Shapes
import com.example.hotel.ui.theme.textPrimaryColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit = {},
    onValueChange: (value: String) -> Unit,
    enable: Boolean = true,
) {
    var isFocus by remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSearchClick() }
//            .height(55.dp)
            .onFocusEvent { focusState ->
                isFocus = focusState.isFocused
            },
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = if (isFocus) MaterialTheme.colors.primary.copy(alpha = 0.1f) else MaterialTheme.colors.onSecondary,
            cursorColor = MaterialTheme.colors.primary,
            disabledLabelColor = MaterialTheme.colors.onSecondary,
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            disabledLeadingIconColor = Gray500,
            disabledTrailingIconColor = Gray500,
            leadingIconColor = if (isFocus) MaterialTheme.colors.primary else Gray500,
            trailingIconColor = if (isFocus) MaterialTheme.colors.primary else Gray500,
        ),
        shape = Shapes.medium,
        enabled = enable,
        placeholder = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.body2.copy(
                    color = Gray500,
                    fontWeight = FontWeight.Normal
                )
            )
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.body2.copy(MaterialTheme.colors.textPrimaryColor),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_light),
                contentDescription = "search"
            )
        },
        trailingIcon = {
            IconButton(onClick = onFilterClick) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_light),
                    contentDescription = "filter"
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
    )
}