package com.example.aksharadeepatutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.aksharadeepatutor.navigation.TutorNavGraph
import com.example.aksharadeepatutor.ui.theme.AksharaDeepaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AksharaDeepaTheme {
                TutorNavGraph()
            }
        }
    }
}
