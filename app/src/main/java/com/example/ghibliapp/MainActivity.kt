package com.example.ghibliapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ghibliapp.navigation.NavManager
import com.example.ghibliapp.ui.theme.GhibliAppTheme
import com.example.ghibliapp.viewmodels.FilmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: FilmViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            GhibliAppTheme {
                NavManager(viewModel)
            }
        }
    }
}