package com.example.hotel.ui.screen.confirmbooking

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.domain.model.DialogContent
import com.example.hotel.ui.composable.CalendarTextFiled
import com.example.hotel.ui.composable.CustomButton
import com.example.hotel.ui.composable.HomeAppBar
import com.example.hotel.ui.composable.LinearHotelItem
import com.example.hotel.ui.composable.PhoneNumberTextFiled
import com.example.hotel.ui.composable.SuccessDialog
import com.example.hotel.ui.composable.confirmhotel.BookingDetails
import com.example.hotel.ui.composable.confirmhotel.BookingDetailsCard
import com.example.hotel.ui.screen.auth.signin.navigateToSignIn
import com.example.hotel.ui.screen.booking.BookingViewModel
import com.example.hotel.ui.screen.main.navigateToMain
import com.example.hotel.ui.theme.bottomPaddingValue
import com.example.hotel.ui.theme.cardBackground
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textSecondaryColor
import com.example.hotel.ui.theme.topPaddingValue
import com.example.hotel.ui.theme.verticalSpacing

@Composable
fun ConfirmBookingScreen(
    navController: NavController,
    arguments: Bundle,
    viewModel: ConfirmBookingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    if (state.dialogShowed) {
        SuccessDialog(
            onDismissRequest = viewModel::onDismissRequest,
            onActionClick = { navController.navigateToMain() },
            dialogContent = DialogContent(
                image = R.drawable.success_booking,
                title = stringResource(id = R.string.congratulations),
                subTitle = "Hotel has booked Successfully",
                actionTitle = "Go to Home",
            )
        )
    }

    if (state.dialogErrorShowed) {
        SuccessDialog(
            onDismissRequest = viewModel::onDismissRequest,
            onActionClick = { navController.navigateToMain() },
            dialogContent = DialogContent(
                image = R.drawable.success_booking,
                title = stringResource(id = R.string.congratulations),
                subTitle = "Hotel has booked Failed",
                actionTitle = "Try another one",
            )
        )
    }

    Column(modifier = Modifier.padding(top = topPaddingValue(), bottom = bottomPaddingValue())) {
        HomeAppBar(
            title = stringResource(id = R.string.booking),
            actions = {}
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.height(25.dp))
            CalendarTextFiled(
                value = state.checkIn,
                onValueChange = viewModel::onChangeCheckIn,
                context = LocalContext.current,
                label = "Check in date"
            )
            Spacer(modifier = Modifier.height(25.dp))
            CalendarTextFiled(
                value = state.checkOut,
                onValueChange = viewModel::onChangeCheckOut,
                context = LocalContext.current,
                label = "Check out date"
            )
            Spacer(modifier = Modifier.height(25.dp))
            PhoneNumberTextFiled(
                value = state.gustCount,
                onValueChange = viewModel::onChangeGuest,
                action = ImeAction.Done,
                label = "Guest number"
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
                    viewModel.onBookingClick(
                        hotelId = arguments.getString("hotelId")!!,
                        price = arguments.getString("price")!!,
                    )
                },
                enabled = if (state.isLoading) false else viewModel.isContinueButtonEnable(),
            )
        }
    }
}
