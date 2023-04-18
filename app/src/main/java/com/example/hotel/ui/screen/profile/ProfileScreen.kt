package com.example.hotel.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.HomeAppBar
import com.example.hotel.ui.composable.ProfilePicture
import com.example.hotel.ui.composable.SettingItem
import com.example.hotel.ui.theme.Red500
import com.example.hotel.ui.theme.textPrimaryColor

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    ProfileContent(
        navController = navController,
    )
}

@Composable
private fun ProfileContent(
    navController: NavController,
) {
    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        HomeAppBar(
            title = stringResource(id = R.string.profile),
        ) {

        }

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
//                    ProfilePicture(url = "https://icon-library.com/images/icon-of-a-person/icon-of-a-person-7.jpg") {
//
//                    }
                    Text(
                        text = "Samer Mushtaha",
                        style = MaterialTheme.typography.h5.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
                    Text(
                        text = "samermushtaha0@gmail.com",
                        style = MaterialTheme.typography.button.copy(color = MaterialTheme.colors.textPrimaryColor)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                    Divider()
                    SettingItem(
                        icon = R.drawable.profile_light,
                        title = stringResource(id = R.string.edit_profile),
                        onClick = {})
                    SettingItem(
                        icon = R.drawable.wallet_light,
                        title = stringResource(id = R.string.payment),
                        onClick = {})
                    SettingItem(
                        icon = R.drawable.notification_light,
                        title = stringResource(id = R.string.notifications),
                        onClick = {})
                    SettingItem(
                        icon = R.drawable.shield_done_light,
                        title = stringResource(id = R.string.security),
                        onClick = {})
                    SettingItem(
                        icon = R.drawable.info_square_light,
                        title = stringResource(id = R.string.help),
                        onClick = {})
                    SettingItem(
                        icon = R.drawable.logout_light,
                        title = stringResource(id = R.string.logout),
                        textColor = Red500,
                        onClick = {})
                }
            }
        }
    }
}