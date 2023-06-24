package com.example.hotel.ui.screen.auth.setupprofile

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.auth.signin.navigateToSignIn
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingXMedium
import com.example.hotel.ui.theme.topPaddingValue
import com.example.hotel.ui.theme.verticalSpacing
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupProfileScreen(
    navController: NavController,
    arguments: Bundle,
    viewModel: SetupProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val context = LocalContext.current

    LaunchedEffect(state.isSuccess){
        if (state.isSuccess) {
            navController.navigateToSignIn()
        }
    }

    CustomBottomSheet(
        modalSheetState = modalSheetState,
        sheetContent = {
            GenderBottomSheet(
                value = state.gender,
                genders = viewModel.genders,
                onSelectedChange = {
                    viewModel.onChangeGender(it)
                    coroutineScope.launch { modalSheetState.hide() }
                }
            )
        },
        screenContent = {
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
                topBar = {
                    DefaultAppBar(
                        title = stringResource(id = R.string.fill_your_profile),
                        onBackClick = { navController.popBackStack() }
                    )
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = horizontalSpacing,
                            vertical = verticalSpacing
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(spacingXMedium)
                    ) {
                        ProfilePicture(
                            url = state.profilePicture,
                            onImageChange = viewModel::onChangeImage,
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            InputTextFiled(
                                modifier = Modifier.fillMaxWidth(0.47f),
                                label = stringResource(id = R.string.first_name),
                                value = state.firstName,
                                onValueChange = viewModel::onChangeFirstName,
                            )
                            InputTextFiled(
                                label = stringResource(id = R.string.last_name),
                                value = state.lastName,
                                onValueChange = viewModel::onChangeLastName,
                            )
                        }
                        CalendarTextFiled(
                            value = state.birthdate,
                            onValueChange = viewModel::onChangeBirthdate,
                            context = LocalContext.current
                        )
                        PhoneNumberTextFiled(
                            value = state.phoneNumber,
                            onValueChange = viewModel::onChangePhoneNumber,
                            action = ImeAction.Done
                        )
                        GenderTextFiled(
                            value = state.gender,
                            onValueChange = {
                                viewModel.onChangeGender(it)
                                coroutineScope.launch { modalSheetState.hide() }
                            },
                            onSelectGenderClick = {
                                coroutineScope.launch { modalSheetState.show() }
                            },
                        )
                    }
                    CustomButton(
                        modifier = Modifier.padding(
                            horizontal = horizontalSpacing,
                            vertical = verticalSpacing
                        ),
                        title = if (state.isLoading) stringResource(id = R.string.loading) else stringResource(
                            id = R.string.contenue
                        ),
                        onClick = {
                            viewModel.onContinueClick(
                                email = arguments.getString("email")!!,
                                password = arguments.getString("password")!!,
                                context
                            )
                        },
                        enabled = if (state.isLoading) false else viewModel.isContinueButtonEnable(),
                    )
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
        })
}