package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextFiled(
    modifier: Modifier = Modifier,
    value: String,
    label: Int = R.string.password,
    action: ImeAction = ImeAction.Next,
    onValueChange: (value: String) -> Unit
) {
    var isFocus by remember {
        mutableStateOf(false)
    }
    var passwordVisible by remember {
        mutableStateOf(true)
    }
    var passwordIcon by remember {
        mutableStateOf(R.drawable.hide_bold)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(heightInput)
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
            leadingIconColor = if (isFocus) MaterialTheme.colors.primary else Gray500,
            trailingIconColor = if (isFocus) MaterialTheme.colors.primary else Gray500,
        ),
        shape = Shapes.medium,
        placeholder = {
            Text(
                text = if (isFocus) "" else stringResource(id = label),
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
                painter = painterResource(id = R.drawable.password_bold),
                contentDescription = "password"
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisible = !passwordVisible
                passwordIcon = if(passwordVisible) R.drawable.hide_bold else R.drawable.show_bold
            }) {
                Icon(
                    painter = painterResource(id = passwordIcon),
                    contentDescription = "hide"
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = action),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None
    )
}