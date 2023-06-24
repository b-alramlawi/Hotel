package com.example.hotel.ui.screen.onboarding

import android.app.Activity
import android.content.Context
import android.view.Window
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hotel.R
import com.example.hotel.ui.composable.CustomButton
import com.example.hotel.ui.composable.Indicator
import com.example.hotel.ui.composable.onboarding.OnBoardingItem
import com.example.hotel.ui.screen.auth.signup.navigateToSignUp
import com.example.hotel.ui.screen.onboarding.state.OnBoardingUiState
import com.example.hotel.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val statePager = rememberPagerState()
    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    OnBoardingContent(
        state = state,
        statePager = statePager,
        paddingValues = paddingValues,
        onNextClick = {
            if (statePager.currentPage != state.pages.size - 1) {
                scope.launch { statePager.animateScrollToPage(statePager.currentPage + 1) }
            } else {
                navController.navigateToSignUp()
            }
        },
        onSkipClick = { navController.navigateToSignUp() },
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingContent(
    state: OnBoardingUiState,
    paddingValues: PaddingValues,
    statePager: PagerState,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .padding(bottom = paddingValues.calculateBottomPadding()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalPager(
            count = state.pages.size,
            state = statePager,
        ) { index ->
            OnBoardingItem(page = state.pages[index])
        }
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Indicator(
                modifier = Modifier.padding(vertical = spacingLarge),
                size = state.pages.size,
                currentPage = statePager.currentPage
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = horizontalSpacing, vertical = verticalSpacing),
            verticalArrangement = Arrangement.spacedBy(spacingXSmall),
        ) {
            CustomButton(
                title = stringResource(id = R.string.next),
                onClick = onNextClick
            )
            AnimatedVisibility(visible = statePager.currentPage != state.pages.size - 1) {
                CustomButton(
                    title = stringResource(id = R.string.skip),
                    color = MaterialTheme.colors.secondary,
                    textColor = MaterialTheme.colors.textForthColor,
                    onClick = onSkipClick
                )
            }
        }
    }
}