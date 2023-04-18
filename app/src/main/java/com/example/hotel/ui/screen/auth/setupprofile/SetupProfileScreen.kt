package com.example.hotel.ui.screen.auth.setupprofile

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingLarge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
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
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val context = LocalContext.current

    SetupProfileContent(
        scrollState = scrollState,
        modalSheetState = modalSheetState,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
        state = state,
        context = context,
        genders = viewModel.genders,
        onChangeFirstName = viewModel::onChangeFirstName,
        isContinueButtonEnable = viewModel.isContinueButtonEnable(),
        onChangeLastName = viewModel::onChangeLastName,
        onChangeFile = viewModel::onChangeFile,
        onChangeImage = viewModel::onChangeImage,
        onChangeBirthdate = viewModel::onChangeBirthdate,
        onChangePhoneNumber = viewModel::onChangePhoneNumber,
        onChangeGender = viewModel::onChangeGender,
        onSelectedGenderChange = viewModel::onChangeGender,
        onBackClick = { navController.popBackStack() },
        onContinueClick = { navController.navigateToMain() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SetupProfileContent(
    state: SetUpProfileUiState,
    scrollState: ScrollState,
    scaffoldState: ScaffoldState,
    modalSheetState: ModalBottomSheetState,
    context: Context,
    isContinueButtonEnable: Boolean,
    coroutineScope: CoroutineScope,
    genders: ArrayList<Gender>,
    onChangeFirstName: (String) -> Unit,
    onChangeFile: (MultipartBody.Part) -> Unit,
    onChangeImage: (Uri) -> Unit,
    onChangeLastName: (String) -> Unit,
    onChangeBirthdate: (String) -> Unit,
    onChangePhoneNumber: (String) -> Unit,
    onChangeGender: (String) -> Unit,
    onSelectedGenderChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    CustomBottomSheet(
        modalSheetState = modalSheetState,
        sheetContent = {
            GenderBottomSheet(
                value = state.gender,
                genders = genders,
                onSelectedChange = {
                    onSelectedGenderChange(it)
                    coroutineScope.launch { modalSheetState.hide() }
                }
            )
        },
        screenContent = {
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),
                topBar = { DefaultAppBar(title = stringResource(id = R.string.fill_your_profile), onBackClick = onBackClick) }
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontalSpacing)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(spacingLarge)
                    ) {
                        ProfilePicture(
                            context = context,
                            url = state.profilePicture,
                            onImageChange = onChangeImage,
                            onFileChange = onChangeFile
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
                            context = context
                        )
                        PhoneNumberTextFiled(
                            value = state.phoneNumber,
                            onValueChange = onChangePhoneNumber,
                            action = ImeAction.Done
                        )
                        GenderTextFiled(
                            value = state.gender,
                            onValueChange = onChangeGender,
                            onSelectGenderClick = { coroutineScope.launch { modalSheetState.show() } }
                        )
                    }
                    CustomButton(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        title = stringResource(id = R.string.contenue),
                        onClick = onContinueClick,
                        enabled = isContinueButtonEnable,
                    )
                }
            }
        })
}