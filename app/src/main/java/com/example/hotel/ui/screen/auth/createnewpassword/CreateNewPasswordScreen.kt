package com.example.hotel.ui.screen.auth.createnewpassword

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.domain.model.DialogContent
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.auth.signin.navigateToSignIn
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingXMedium
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.topPaddingValue
import com.example.hotel.ui.theme.verticalSpacing

@Composable
fun CreateNewPasswordScreen(
    navController: NavController,
    arguments: Bundle,
    viewModel: CreateNewPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
        topBar = {
            DefaultAppBar(
                title = stringResource(id = R.string.create_new_password),
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { padding ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shield),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier.padding(
                    horizontal = horizontalSpacing,
                    vertical = verticalSpacing
                ),
                verticalArrangement = Arrangement.spacedBy(spacingXMedium)
            ) {
                Text(
                    text = stringResource(id = R.string.create_new_password),
                    style = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.textPrimaryColor)
                )
                PasswordTextFiled(
                    value = state.newPassword,
                    onValueChange = viewModel::onChangeNewPassword
                )
                PasswordTextFiled(
                    value = state.confirmPassword,
                    label = R.string.confirm_password,
                    onValueChange = viewModel::onChangeConfirmPassword
                )
                CustomCheckbox(
                    title = stringResource(id = R.string.remember_me),
                    value = state.rememberMe,
                    onValueChange = viewModel::onChangeRemember
                )
                CustomButton(
                    title = if (state.isLoading) stringResource(id = R.string.loading) else stringResource(
                        id = R.string.contenue
                    ),
                    enabled = if (state.isLoading) false else viewModel.isContenueEnable(),
                    onClick = { viewModel.onContinueClick(arguments.getString("email")!!) }
                )
            }
            if (state.dialogShowed) {
                SuccessDialog(
                    onDismissRequest = viewModel::onDismissRequest,
                    onActionClick = { navController.navigateToSignIn() },
                    dialogContent = DialogContent(
                        image = R.drawable.success_booking,
                        title = stringResource(id = R.string.congratulations),
                        subTitle = stringResource(id = R.string.your_account_is_ready_to_use),
                        actionTitle = stringResource(id = R.string.go_to_sign_in),
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