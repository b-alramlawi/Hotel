package com.example.hotel.ui.screen.auth.signup

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
import com.example.hotel.ui.screen.auth.setupprofile.navigateToSetupProfile
import com.example.hotel.ui.screen.auth.signin.navigateToSignIn
import com.example.hotel.ui.screen.auth.signup.state.SignUpUiState
import com.example.hotel.ui.theme.*

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()

    SignUpContent(
        state = state,
        scrollState = scrollState,
        scaffoldState = scaffoldState,
        isSignUpEnable = viewModel.isSignUpEnable(),
        onChangeEmail = viewModel::onChangeEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangeRememberCheck = viewModel::onChangeRememberCheck,
        onSignUpClick = { email, password ->
            navController.navigateToSetupProfile(
                email,
                password
            )
        },
        onSignInClick = { navController.navigateToSignIn() },
        onGoogleClick = viewModel::onGoogleClick,
        onBackClick = { navController.popBackStack() },
    )
}

@Composable
fun SignUpContent(
    state: SignUpUiState,
    scrollState: ScrollState,
    scaffoldState: ScaffoldState,
    isSignUpEnable: Boolean,
    onChangeEmail: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeRememberCheck: (Boolean) -> Unit,
    onSignUpClick: (email: String, password: String) -> Unit,
    onSignInClick: () -> Unit,
    onGoogleClick: () -> Unit,
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
                text = stringResource(id = R.string.create_your_account),
                style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )
            Column(verticalArrangement = Arrangement.spacedBy(spacingXMedium)) {
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
                    title = stringResource(id = R.string.sign_up),
                    enabled = isSignUpEnable,
                    onClick = { onSignUpClick(state.email, state.password) }
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
                message = stringResource(id = R.string.already_have_an_account),
                textButton = stringResource(id = R.string.sign_in),
                onClick = onSignInClick
            )
        }
    }
}
