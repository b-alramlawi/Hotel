package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.hotel.R
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textForthColor
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun SuccessDialog() {
    Dialog(onDismissRequest = { }) {
        Card(backgroundColor = MaterialTheme.colors.background, shape = RoundedCornerShape(24.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.success_booking),
                    contentDescription = "success_booking"
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Modal Title",
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Lorem ipsum dolor sit amet hua qui lori ipsum sit ghui amet poety amet",
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.textSecondaryColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(
                    title = "Button",
                    color = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.textFifthColor
                ) {

                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomButton(
                    title = "Button",
                    color = MaterialTheme.colors.secondary,
                    textColor = MaterialTheme.colors.textForthColor
                ) {

                }
            }
        }
    }
}