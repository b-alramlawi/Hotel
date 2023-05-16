package com.example.hotel.ui.screen.auth.setupprofile

import android.net.Uri
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
import com.example.hotel.domain.model.Gender
import com.example.hotel.ui.composable.*
import com.example.hotel.ui.screen.auth.setupprofile.state.SetUpProfileUiState
import com.example.hotel.ui.screen.main.navigateToMain
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingXMedium
import com.example.hotel.ui.theme.topPaddingValue
import com.example.hotel.ui.theme.verticalSpacing
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupProfileScreen(
    navController: NavController,
    viewModel: SetupProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    SetupProfileContent(
        modalSheetState = modalSheetState,
        state = state,
        genders = viewModel.genders,
        onChangeFirstName = viewModel::onChangeFirstName,
        isContinueButtonEnable = viewModel.isContinueButtonEnable(),
        onChangeLastName = viewModel::onChangeLastName,
        onChangeImage = viewModel::onChangeImage,
        onChangeBirthdate = viewModel::onChangeBirthdate,
        onChangePhoneNumber = viewModel::onChangePhoneNumber,
        onChangeGender = viewModel::onChangeGender,
        onSelectedGenderChange = {
            viewModel.onChangeGender(it)
            coroutineScope.launch { modalSheetState.hide() }
        },
        onSelectGenderClick = { coroutineScope.launch { modalSheetState.show() } },
        onBackClick = { navController.popBackStack() },
        onContinueClick = { navController.navigateToMain() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupProfileContent(
    state: SetUpProfileUiState,
    modalSheetState: ModalBottomSheetState,
    isContinueButtonEnable: Boolean,
    genders: ArrayList<Gender>,
    onChangeFirstName: (String) -> Unit,
    onChangeImage: (Uri) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeBirthdate: (String) -> Unit,
    onChangePhoneNumber: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onSelectedGenderChange: (String) -> Unit,
    onSelectGenderClick: () -> Unit,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    CustomBottomSheet(
        modalSheetState = modalSheetState,
        sheetContent = {
            GenderBottomSheet(
                value = state.gender,
                genders = genders,
                onSelectedChange = onSelectedGenderChange
            )
        },
        screenContent = {
            Scaffold(
                scaffoldState = rememberScaffoldState(),
                modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue()),
                topBar = {
                    DefaultAppBar(
                        title = stringResource(id = R.string.fill_your_profile),
                        onBackClick = onBackClick
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
                            onImageChange = onChangeImage,
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                            InputTextFiled(
                                modifier = Modifier.fillMaxWidth(0.47f),
                                label = stringResource(id = R.string.first_name),
                                value = state.firstName,
                                onValueChange = onChangeFirstName,
                            )
                            InputTextFiled(
                                label = stringResource(id = R.string.last_name),
                                value = state.lastName,
                                onValueChange = onChangeLastName,
                            )
                        }
                        CalendarTextFiled(
                            value = state.birthdate,
                            onValueChange = onChangeBirthdate,
                            context = LocalContext.current
                        )
                        PhoneNumberTextFiled(
                            value = state.phoneNumber,
                            onValueChange = onChangePhoneNumber,
                            action = ImeAction.Done
                        )
                        GenderTextFiled(
                            value = state.gender,
                            onValueChange = onChangeGender,
                            onSelectGenderClick = onSelectGenderClick
                        )
                    }
                    CustomButton(
                        modifier = Modifier.padding(
                            horizontal = horizontalSpacing,
                            vertical = verticalSpacing
                        ),
                        title = stringResource(id = R.string.contenue),
                        onClick = onContinueClick,
                        enabled = isContinueButtonEnable,
                    )
                }
            }
        })
}