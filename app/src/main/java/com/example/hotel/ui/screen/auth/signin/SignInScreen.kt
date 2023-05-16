package com.example.hotel.ui.screen.auth.signin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.domain.model.DialogContent
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.composable.auth.DividerWithText
import com.example.hotel.ui.screen.auth.signin.state.SignInUiState
import com.example.hotel.ui.screen.auth.signup.navigateToSignUp
import com.example.hotel.ui.screen.main.navigateToMain
import com.example.hotel.ui.theme.*

@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()

    SignInContent(
        state = state,
        isSignInEnable = viewModel.isSignInEnable(),
        onChangeEmail = viewModel::onChangeEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangeRememberCheck = viewModel::onChangeRememberCheck,
        onSignUpClick = { navController.navigateToSignUp() },
        onSignInClick = { navController.navigateToMain() },
        onGoogleClick = viewModel::onGoogleClick,
        onForgotPasswordClick = viewModel::onForgetPasswordClick,
        onBackClick = { navController.popBackStack() },
        onDismissRequest = viewModel::onDismissRequest,
        onGmailClick = {}
    )
}

@Composable
fun SignInContent(
    state: SignInUiState,
    isSignInEnable: Boolean,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeRememberCheck: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit,
    onGmailClick: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
        topBar = { DefaultAppBar(title = "", onBackClick = onBackClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = horizontalSpacing,
                    vertical = verticalSpacing
                ),
                text = stringResource(id = R.string.login_to_your_account),
                style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )
            Column(
                modifier = Modifier.padding(
                    horizontal = horizontalSpacing,
                    vertical = verticalSpacing
                ),
                verticalArrangement = Arrangement.spacedBy(spacingXMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputTextFiled(
                    leadingIcon = R.drawable.message_bold,
                    label = stringResource(id = R.string.email),
                    value = state.email,
                    onValueChange = onChangeEmail,
                    keyboardType = KeyboardType.Email,
                    action = ImeAction.Next
                )
                PasswordTextFiled(value = state.password, onValueChange = onChangePassword)
                CustomCheckbox(
                    title = stringResource(id = R.string.remember_me),
                    value = state.rememberMe,
                    onValueChange = onChangeRememberCheck
                )
                CustomButton(
                    title = stringResource(id = R.string.sign_in),
                    enabled = isSignInEnable,
                    onClick = onSignInClick
                )
                CustomTextButton(
                    title = stringResource(id = R.string.forgot_password),
                    onClick = onForgotPasswordClick
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(spacingXMedium),
                modifier = Modifier.padding(
                    horizontal = horizontalSpacing,
                    vertical = verticalSpacing
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DividerWithText()
                IconCustomButton(
                    title = stringResource(id = R.string.sign_in_with_google),
                    color = MaterialTheme.colors.background,
                    icon = R.drawable.google,
                    onClick = onGoogleClick
                )
            }
            Footer(
                modifier = Modifier.padding(horizontal = horizontalSpacing),
                message = stringResource(id = R.string.dont_have_an_account),
                textButton = stringResource(id = R.string.sign_up),
                onClick = onSignUpClick
            )
            if (state.dialogShowed) {
                SuccessDialog(
                    onDismissRequest = onDismissRequest,
                    onActionClick = onGmailClick,
                    dialogContent = DialogContent(
                        image = R.drawable.success_booking,
                        title = stringResource(id = R.string.check_your_email),
                        subTitle = stringResource(id = R.string.we_have_sent_an_email_to_reset_your_password),
                        actionTitle = stringResource(id = R.string.go_to_gmail),
                    )
                )
            }
        }
    }
}
