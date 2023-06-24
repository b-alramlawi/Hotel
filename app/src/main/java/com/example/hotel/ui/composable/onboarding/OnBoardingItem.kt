package com.example.hotel.ui.composable.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hotel.domain.model.Page
import com.example.hotel.ui.theme.horizontalSpacing
import com.example.hotel.ui.theme.spacingHuge
import com.example.hotel.ui.theme.spacingMedium
import com.example.hotel.ui.theme.textPrimaryColor
import com.example.hotel.ui.theme.textThirdColor

@Composable
fun OnBoardingItem(page: Page){
    Column(verticalArrangement = Arrangement.spacedBy(spacingHuge)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.50f)
        ){
            Image(
                painter = painterResource(id = page.image),
                contentDescription = "image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(spacingMedium),
            modifier = Modifier.padding(horizontal = horizontalSpacing)
        ) {
            Text(
                text = stringResource(id = page.title),
                style = MaterialTheme.typography.h3.copy(
                    color = MaterialTheme.colors.textPrimaryColor,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = stringResource(id = page.subtitle),
                style = MaterialTheme.typography.body2.copy(
                    color = MaterialTheme.colors.textThirdColor,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify
                )
            )
        }
    }
}