package com.example.hotel.ui.screen.auth.forgotpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.DefaultAppBar
import com.example.hotel.ui.composable.CustomButton
import com.example.hotel.ui.composable.VerificationCode
import com.example.hotel.ui.screen.auth.createnewpassword.navigateToCreateNewPassword
import com.example.hotel.ui.screen.auth.forgotpassword.state.ForgotPasswordUiState
import com.example.hotel.ui.theme.White
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    ForgotPasswordContent(
        state = state,
        onBackClick = { navController.popBackStack() },
        onVerifyChange = viewModel::onChangeVerificationCode,
        onResendCodeClick = viewModel::onResendCodeClick,
        onVerifyClick = { navController.navigateToCreateNewPassword() }
    )
}

@Composable
fun ForgotPasswordContent(
    state: ForgotPasswordUiState,
    onBackClick: () -> Unit,
    onResendCodeClick: () -> Unit,
    onVerifyClick: () -> Unit,
    onVerifyChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ) {
        Column() {
            DefaultAppBar(
                title = stringResource(id = R.string.forgot_password),
                onBackClick = onBackClick
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = MaterialTheme.colors.textPrimaryColor,
                        fontWeight = FontWeight.Normal
                    )) {
                        append(stringResource(id = R.string.code_has_been_send_to))
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.textPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append("\n${state.email}")
                    }
                },
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.textPrimaryColor,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center
            )
            VerificationCode(value = state.verificationValues, onValueChange = onVerifyChange)
            if (state.seconds == 0) {
                TextButton(
                    onClick = onResendCodeClick
                ) {
                    Text(
                        text = stringResource(id = R.string.resend_code),
                        style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primary)
                    )
                }
            } else {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(
                            color = MaterialTheme.colors.textPrimaryColor,
                            fontWeight = FontWeight.Normal
                        )) {
                            append(stringResource(id = R.string.resend_code_in))
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(" ${state.seconds}")
                        }
                    },
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.textPrimaryColor,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        CustomButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            title = stringResource(id = R.string.verify),
            color = MaterialTheme.colors.primary,
            textColor = White,
            onClick = onVerifyClick
        )
    }
}