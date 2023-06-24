package com.example.hotel.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.hotel.domain.model.DialogContent
import com.example.hotel.ui.theme.spacingHuge
import com.example.hotel.ui.theme.spacingMedium
import com.example.hotel.ui.theme.textFifthColor
import com.example.hotel.ui.theme.textSecondaryColor

@Composable
fun SuccessDialog(
    dialogContent: DialogContent,
    onDismissRequest: () -> Unit,
    onActionClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
        Card(backgroundColor = MaterialTheme.colors.background, shape = RoundedCornerShape(24.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(spacingHuge),
                verticalArrangement = Arrangement.spacedBy(spacingHuge)
            ) {
                Image(
                    painter = painterResource(id = dialogContent.image),
                    contentDescription = "image"
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(spacingMedium)
                ) {
                    Text(
                        text = dialogContent.title,
                        style = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.primary, textAlign = TextAlign.Center)
                    )
                    Text(
                        text = dialogContent.subTitle,
                        style = MaterialTheme.typography.body2.copy(
                            color = MaterialTheme.colors.textSecondaryColor,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    )
                }
                CustomButton(
                    title = dialogContent.actionTitle,
                    color = MaterialTheme.colors.primary,
                    textColor = MaterialTheme.colors.textFifthColor,
                    onClick = onActionClick
                )
            }
        }
    }
}