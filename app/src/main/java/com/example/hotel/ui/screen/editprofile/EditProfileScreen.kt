package com.example.hotel.ui.screen.editprofile

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.hotel.ui.screen.editprofile.state.EditProfileUiState
import com.example.hotel.ui.theme.Green300
import com.example.hotel.ui.theme.White
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.topPaddingValue
import com.example.hotel.ui.theme.verticalSpacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.popBackStack()
        }
    }

    EditProfileContent(
        state = state,
        onChangeLastName = viewModel::onChangeLastName,
        onChangeFirstName = viewModel::onChangeFirstName,
        onChangeBirthdate = viewModel::onChangeBirthdate,
        onChangePhoneNumber = viewModel::onChangePhoneNumber,
        onChangeGender = viewModel::onChangeGender,
        onDatePickerClick = {},
        isContinueButtonEnable = viewModel.isContinueButtonEnable(),
        onContinueClick = { viewModel.onContinueClick(it) },
        coroutineScope = coroutineScope,
        onChangeImage = viewModel::onChangeImage,
        context = context,
        viewModel = viewModel,
        modalSheetState = modalSheetState,
        onBackClick = {navController.popBackStack()}
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun EditProfileContent(
    viewModel: EditProfileViewModel,
    modalSheetState: ModalBottomSheetState,
    state: EditProfileUiState,
    coroutineScope: CoroutineScope,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeImage: (Any) -> Unit,
    onChangeBirthdate: (String) -> Unit,
    onChangePhoneNumber: (String) -> Unit,
    onChangeGender: (String) -> Unit,
//    onContinueClick: (Context) -> Unit,
    onDatePickerClick: () -> Unit,
    isContinueButtonEnable: Boolean,
    onContinueClick: (Context) -> Unit,
    onBackClick: () -> Unit,
    context: Context
) {

    if (state.isLoading) {
        CircularProgressIndicator()
    } else if (state.isFailed) {
        Text(
            text = stringResource(id = R.string.error),
            style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
        )
    } else {
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
                Column {
                    DefaultAppBar(
                        title = stringResource(id = R.string.edit_profile),
                        onBackClick = {onBackClick()})
                    Column(
                        modifier = Modifier.padding(
                            top = topPaddingValue(),
                            bottom = bottomPaddingValue(),
                            start = 24.dp,
                            end = 24.dp
                        ), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfilePicture(
                            url = state.profilePicture,
                            onImageChange = onChangeImage,
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            InputTextFiled(
                                modifier = Modifier.fillMaxWidth(0.47f),
                                label = stringResource(id = R.string.first_name),
                                value = state.firstName,
                                onValueChange = onChangeFirstName,
                            )
                            InputTextFiled(
                                modifier = Modifier.fillMaxWidth(1f),
                                label = stringResource(id = R.string.last_name),
                                value = state.lastName,
                                onValueChange = onChangeLastName,
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        CalendarTextFiled(
                            value = state.birthdate,
                            onValueChange = onChangeBirthdate,
                            context = LocalContext.current
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        PhoneNumberTextFiled(
                            value = state.phoneNumber,
                            onValueChange = onChangePhoneNumber,
                            action = ImeAction.Done
                        )
                        Spacer(modifier = Modifier.height(20.dp))
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
                        Spacer(modifier = Modifier.weight(1f))
                        CustomButton(
                            modifier = Modifier.padding(
                                vertical = verticalSpacing
                            ),
                            title = if (state.isLoading) stringResource(id = R.string.loading) else stringResource(
                                id = R.string.contenue
                            ),
                            onClick = {
                                onContinueClick(context)
                            },
                            enabled = if (state.isLoading) false else isContinueButtonEnable,
                        )
                    }
                }
            }
        )
    }
}