package com.example.hotel.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hotel.ui.composable.CustomButton
import com.example.hotel.ui.composable.CustomTextFiled
import com.example.hotel.ui.theme.HotelTheme
import com.example.hotel.ui.theme.textFifthColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HotelTheme {
                Box {
                    CustomButton(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 20.dp),
                        title = "Sign in",
                        color = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.textFifthColor,
                        enabled = false
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HotelTheme {
        Greeting("Android")
    }
}