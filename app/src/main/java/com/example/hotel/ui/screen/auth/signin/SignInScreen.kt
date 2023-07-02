package com.example.hotel.ui.screen.auth.signin

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.domain.model.DialogContent
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.composable.auth.DividerWithText
import com.example.hotel.ui.screen.auth.forgotpassword.navigateToForgotPassword
import com.example.hotel.ui.screen.main.navigateToMain
import com.example.hotel.ui.theme.*

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigateToMain()
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
        topBar = { DefaultAppBar(title = "", onBackClick = { navController.popBackStack() }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding),
//            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = horizontalSpacing,
                    vertical = verticalSpacing
                ),
                text = stringResource(id = R.string.login_to_your_account),
                style = MaterialTheme.typography.h1.copy(color = MaterialTheme.colors.textPrimaryColor)
            )
            Spacer(modifier = Modifier.weight(0.3f))
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
                    onValueChange = viewModel::onChangeEmail,
                    keyboardType = KeyboardType.Email,
                    action = ImeAction.Next
                )
                PasswordTextFiled(
                    value = state.password,
                    onValueChange = viewModel::onChangePassword
                )
                CustomCheckbox(
                    title = stringResource(id = R.string.remember_me),
                    value = state.rememberMe,
                    onValueChange = viewModel::onChangeRememberCheck
                )
                CustomButton(
                    title = if (state.isLoading) stringResource(id = R.string.loading) else stringResource(
                        id = R.string.sign_in
                    ),
                    enabled = if (state.isLoading) false else viewModel.isSignInEnable(),
                    onClick = { viewModel.onSignInClick() /*navController.navigateToMain()*/}
                )
                CustomTextButton(
                    title = stringResource(id = R.string.forgot_password),
                    onClick = { viewModel.onForgetPasswordClick() }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
//            Column(
//                verticalArrangement = Arrangement.spacedBy(spacingXMedium),
//                modifier = Modifier.padding(
//                    horizontal = horizontalSpacing,
//                    vertical = verticalSpacing
//                ),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                DividerWithText()
//                IconCustomButton(
//                    title = stringResource(id = R.string.sign_in_with_google),
//                    color = MaterialTheme.colors.background,
//                    icon = R.drawable.google,
//                    onClick = {  }
//                )
//            }
            Footer(
                modifier = Modifier.padding(horizontal = horizontalSpacing),
                message = stringResource(id = R.string.dont_have_an_account),
                textButton = stringResource(id = R.string.sign_up),
                onClick = { navController.popBackStack() }
            )
            if (state.dialogShowed) {
                SuccessDialog(
                    onDismissRequest = viewModel::onDismissRequest,
                    onActionClick = {
                        navController.navigateToForgotPassword(state.email)
                        try {
                            val intent = Intent(Intent.ACTION_MAIN)
                            intent.addCategory(Intent.CATEGORY_APP_EMAIL)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        } catch (e: Exception) {
//                            viewModel.onError(errorOpenEmailApp)
                        }
                    },
                    dialogContent = DialogContent(
                        image = R.drawable.success_booking,
                        title = stringResource(id = R.string.check_your_email),
                        subTitle = stringResource(id = R.string.we_have_sent_an_email_to_reset_your_password),
                        actionTitle = stringResource(id = R.string.go_to_gmail),
                    )
                )
            }
            if (state.errorMessage.isNotEmpty()) {
                LaunchedEffect(Unit) {
                    val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                        message = state.errorMessage,
                    )
                    when (snackBarResult) {
                        SnackbarResult.Dismissed -> viewModel.clearErrorMessage()
                        else -> {}
                    }
                }
            }
        }
    }
}
