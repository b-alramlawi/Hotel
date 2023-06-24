package com.example.hotel.ui.screen.auth.createnewpassword

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hotel.ui.screen.auth.forgotpassword.ForgotPasswordScreenArgs
import com.example.hotel.ui.screen.auth.forgotpassword.ROUTE_FORGOT_PASSWORD

const val ROUTE_CREATE_NEW_PASSWORD = "create_new_password"

fun NavController.navigateToCreateNewPassword(email: String) {
    navigate("$ROUTE_CREATE_NEW_PASSWORD/$email")
}

fun NavGraphBuilder.createNewPasswordRoute(navController: NavController) {
    composable(
        route = "$ROUTE_CREATE_NEW_PASSWORD/{${ResetPasswordScreenArgs.EMAIL}}",
        arguments = listOf(
            navArgument(name = ResetPasswordScreenArgs.EMAIL) { NavType.StringType },
        )
    ) {
        CreateNewPasswordScreen(navController = navController, arguments = it.arguments!!)
    }
}

class ResetPasswordScreenArgs(savedStateHandle: SavedStateHandle) {
    val email: String? = savedStateHandle[EMAIL]

    companion object {
        const val EMAIL = "email"
    }
}