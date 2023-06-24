package com.example.hotel.ui.screen.auth.forgotpassword

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.CustomButton
import com.example.hotel.ui.composable.DefaultAppBar
import com.example.hotel.ui.composable.VerificationCode
import com.example.hotel.ui.screen.auth.createnewpassword.navigateToCreateNewPassword
import com.example.hotel.ui.screen.auth.signin.SignInViewModel
import com.example.hotel.ui.screen.main.navigateToMain
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.topPaddingValue

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    arguments: Bundle,
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigateToCreateNewPassword(arguments.getString("email")!!)
        }
    }

    Column(
        modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultAppBar(
            title = stringResource(id = R.string.forgot_password),
            onBackClick = { navController.popBackStack() })
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.code_has_been_send_to) + "\n" + arguments.getString("email"),
            style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.textPrimaryColor, textAlign = TextAlign.Center)
        )
        VerificationCode(
            modifier = Modifier.padding(horizontalSpacing),
            value = state.verificationCode,
            onValueChange = viewModel::onChangeVerificationCode,
            isError = state.errorMessage.isNotEmpty()
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(
            modifier = Modifier.padding(horizontalSpacing),
            title = if (state.isLoading) stringResource(id = R.string.loading) else stringResource(
                id = R.string.contenue
            ),
            enabled = if (state.isLoading) false else viewModel.isContinueButtonEnable(),
            onClick = { viewModel.onContinueClick(arguments.getString("email")!!) }
        )
//        if (state.errorMessage.isNotEmpty()) {
//            LaunchedEffect(Unit) {
//                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
//                    message = state.errorMessage,
//                )
//                when (snackBarResult) {
//                    SnackbarResult.Dismissed -> viewModel.clearErrorMessage()
//                    else -> {}
//                }
//            }
//        }
    }
}