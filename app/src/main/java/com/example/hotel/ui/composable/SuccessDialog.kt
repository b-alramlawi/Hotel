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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.hotel.R
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textForthColor
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun SuccessDialog(onDismissRequest: () -> Unit, onGoToLoginClick: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
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
                    text = stringResource(id = R.string.congratulations),
                    style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.your_account_is_ready_to_use),
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.textSecondaryColor,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                CustomButton(
                    title = stringResource(id = R.string.go_to_sign_in),
                    color = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.textFifthColor,
                    onClick = onGoToLoginClick
                )
            }
        }
    }
}