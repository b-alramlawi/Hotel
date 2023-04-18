package com.example.hotel.ui.composable

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hotel.R
import com.example.hotel.ui.theme.*
import java.util.Date

@Composable
fun CalendarTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = stringResource(id = R.string.birthdate),
    context: Context
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
                text = label,
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
            IconButton(onClick = { showDatePickerDialog(context, onValueChange) }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_light),
                    contentDescription = "calendar"
                )
            }
        }
    )
}

fun showDatePickerDialog(context: Context, onValueChange: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()
    DatePickerDialog(
        context,
        { _, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            onValueChange("$selectedDay/${selectedMonth + 1}/$selectedYear")
        },
        currentYear, currentMonth, currentDay
    ).show()
}