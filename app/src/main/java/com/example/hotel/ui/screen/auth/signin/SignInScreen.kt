package com.example.hotel.ui.screen.auth.signin

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.composable.auth.DividerWithText
import com.example.hotel.ui.screen.auth.forgotpassword.navigateToForgotPassword
import com.example.hotel.ui.screen.auth.signin.state.SignInUiState
import com.example.hotel.ui.screen.auth.signup.navigateToSignUp
import com.example.hotel.ui.screen.main.navigateToMain
import com.example.hotel.ui.theme.*

@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()

    SignInContent(
        state = state,
        scrollState = scrollState,
        scaffoldState = scaffoldState,
        isSignInEnable = viewModel.isSignInEnable(),
        onChangeEmail = viewModel::onChangeEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangeRememberCheck = viewModel::onChangeRememberCheck,
        onSignUpClick = { navController.navigateToSignUp() },
        onSignInClick = { navController.navigateToMain() },
        onGoogleClick = viewModel::onGoogleClick,
        onForgotPasswordClick = { navController.navigateToForgotPassword() },
        onBackClick = { navController.popBackStack() }
    )
}

@Composable
fun SignInContent(
    state: SignInUiState,
    scrollState: ScrollState,
    scaffoldState: ScaffoldState,
    isSignInEnable: Boolean,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeRememberCheck: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
        topBar = { DefaultAppBar(title = "", onBackClick = onBackClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(horizontal = horizontalSpacing)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = stringResource(id = R.string.login_to_your_account),
                style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )
            Column(
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
                modifier = Modifier.fillMaxWidth(),
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
                message = stringResource(id = R.string.dont_have_an_account),
                textButton = stringResource(id = R.string.sign_up),
                onClick = onSignUpClick
            )
        }
    }
}
