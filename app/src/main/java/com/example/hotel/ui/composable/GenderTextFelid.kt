package com.example.hotel.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hotel.R
import com.example.hotel.ui.theme.*

@Composable
fun GenderTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    onSelectGenderClick: () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(heightInput),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.onSecondary,
            disabledLabelColor = MaterialTheme.colors.onSecondary,
            unfocusedBorderColor = Color.Transparent,
            disabledTrailingIconColor = Gray500,
            disabledBorderColor = Color.Transparent,
            disabledPlaceholderColor = Gray500
        ),
        shape = Shapes.medium,
        placeholder = {
            Text(
                text = stringResource(id = R.string.gender),
                style = MaterialTheme.typography.body2.copy(
                    color = Gray500,
                    fontWeight = FontWeight.Normal
                )
            )
        },
        enabled = false,
        singleLine = true,
        textStyle = MaterialTheme.typography.body2.copy(MaterialTheme.colors.textPrimaryColor),
        trailingIcon = {
            IconButton(onClick = onSelectGenderClick) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_down_2_light),
                    contentDescription = "gender"
                )
            }
        }
    )
}