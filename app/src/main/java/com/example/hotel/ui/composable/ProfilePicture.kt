package com.example.hotel.ui.composable

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.hotel.R

@Composable
fun ProfilePicture(url: Any?, onImageChange: (Uri) -> Unit) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                onImageChange(uri)
            }
        }
    )

    Box {
        Card(
            modifier = Modifier.size(130.dp),
            shape = CircleShape,
            elevation = 0.dp
        ) {
            SubcomposeAsyncImage(
                model = url,
                contentDescription = "profile_picture",
                contentScale = ContentScale.Crop,
                loading = { CircularProgressIndicator() },
            )
        }
        Card(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
            shape = RoundedCornerShape(7.dp),
            backgroundColor = MaterialTheme.colors.primary,
        ) {
            Icon(
                modifier = Modifier.padding(3.dp),
                painter = painterResource(id = R.drawable.edit_bold),
                contentDescription = "edit",
                tint = MaterialTheme.colors.background
            )
        }
    }
}