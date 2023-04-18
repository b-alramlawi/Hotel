package com.example.hotel.ui.composable

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
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
import kotlinx.coroutines.flow.update
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfilePicture(context: Context, url: Any?, onImageChange: (Uri) -> Unit, onFileChange: (MultipartBody.Part) -> Unit) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                onImageChange(uri)
                val file = createFileFromUri(uri, context)
                createFile(file!!, onFileChange)
            }
        }
    )

    Box {
        Card(
            modifier = Modifier.size(140.dp),
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

fun createFileFromUri(uri: Uri, context: Context): File? {
    val contentResolver = context.contentResolver
    val file = File(context.cacheDir, getFileName(uri, contentResolver))
    try {
        contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        return file
    } catch (e: Exception) {
        Log.e("TAG", "Error creating file from uri: ${e.message}")
    }
    return null
}

fun createFile(file: File, onFileChange: (MultipartBody.Part) -> Unit){
    val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
    val body = MultipartBody.Part.createFormData("name", file.name, requestBody)
//    _state.update { it.copy(image = body) }
    onFileChange(body)
}

fun getFileName(uri: Uri, contentResolver: ContentResolver): String {
    val cursor = contentResolver.query(uri, null, null, null, null)
    cursor?.let {
        if (it.moveToFirst()) {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (nameIndex != -1) {
                return it.getString(nameIndex)
            }
        }
        it.close()
    }
    return uri.lastPathSegment ?: "unknown"
}