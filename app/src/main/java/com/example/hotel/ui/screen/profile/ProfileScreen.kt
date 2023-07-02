package com.example.hotel.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.ProfilePicture
import com.example.hotel.ui.composable.SettingItem
import com.example.hotel.ui.screen.auth.signin.navigateToSignIn
import com.example.hotel.ui.screen.editprofile.navigateToEditProfile
import com.example.hotel.ui.theme.Red500
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if(state.isLoading){
        CircularProgressIndicator()
    }else if(state.isFailed){
        Text(
            text = stringResource(id = R.string.error),
            style = MaterialTheme.typography.h4.copy(MaterialTheme.colors.textFifthColor)
        )
    } else if(state.user != null){
        Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfilePicture(
                            url = state.image,
                            onImageChange = viewModel::onChangeImage,
                        )
                        Text(
                            text = state.user!!.firstName + " " + state.user!!.lastName,
                            style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
                        )
                        Text(
                            text = state.user!!.email,
                            style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.textPrimaryColor)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(24.dp), modifier = Modifier.padding(horizontal = horizontalSpacing)) {
                        Divider()
                        SettingItem(
                            icon = R.drawable.profile_light,
                            title = stringResource(id = R.string.edit_profile),
                            onClick = {navController.navigateToEditProfile()})
                        SettingItem(
                            icon = R.drawable.logout_light,
                            title = stringResource(id = R.string.logout),
                            textColor = Red500,
                            onClick = {navController.navigateToSignIn()})
                    }
                }
            }
        }
    }
}