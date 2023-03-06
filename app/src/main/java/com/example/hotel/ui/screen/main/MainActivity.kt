package com.example.hotel.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.hotel.ui.theme.HotelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() {
            HotelTheme {
                MainScreen(rememberNavController(), rememberNavController())
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    HotelTheme {
//        Greeting("Android")
//    }
//}