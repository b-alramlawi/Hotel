package com.example.hotel.ui.screen.editprofile

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.editprofile.state.EditProfileUiState
import com.example.hotel.ui.theme.Green300
import com.example.hotel.ui.theme.White
import kotlinx.coroutines.CoroutineScope

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    EditProfileContent(
        state = state,
        onChangeLastName = viewModel::onChangeLastName,
        onChangeFirstName = viewModel::onChangeFirstName,
        onChangeBirthdate = viewModel::onChangeBirthdate,
        onChangePhoneNumber = viewModel::onChangePhoneNumber,
        onChangeGender = viewModel::onChangeGender,
        onDatePickerClick = {},
        isContinueButtonEnable = false,
        onContinueClick = {},
        coroutineScope = coroutineScope
    )

}

@Composable
private fun EditProfileContent(
    state: EditProfileUiState,
    coroutineScope: CoroutineScope,
    onChangeFirstName: (String) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeBirthdate: (String) -> Unit,
    onChangePhoneNumber: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onDatePickerClick: () -> Unit,
    isContinueButtonEnable: Boolean,
    onContinueClick: () -> Unit
){
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        DefaultAppBar(title = stringResource(id = R.string.edit_profile), onBackClick = {})
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
//        CalendarTextFiled(
//            value = state.birthdate,
//            onValueChange = onChangeBirthdate,
//            onDatePickerClick = onDatePickerClick
//        )
        PhoneNumberTextFiled(
            value = state.phoneNumber,
            onValueChange = onChangePhoneNumber,
            action = ImeAction.Done
        )
        GenderTextFiled(
            value = state.gender,
            onValueChange = onChangeGender,
            onSelectGenderClick = { }
        )
        CustomButton(
            title = stringResource(id = R.string.contenue),
            color = MaterialTheme.colors.primary,
            textColor = White,
            onClick = onContinueClick,
            disableColor = Green300,
            enabled = isContinueButtonEnable,
        )
    }
}