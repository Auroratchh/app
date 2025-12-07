package com.example.gamesapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.gamesapp.shared.MainIconButton

/***
 * Project: Room App
 * Package: com.daniel.roomapp.components
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/04/2025 - 08:59
 * All rights reserved 2025.
 **/

@Composable
fun TitleBar(
    name: String,
) {
    Text(name, fontSize = 25.sp, color = MaterialTheme.colorScheme.onSurface)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CenterAppBar(
    name: String,
    containerColor: Color,
    onNavigationClick: (() -> Unit)? = null,
    onActionButtonClick: (() -> Unit)? = null,
){
    TopAppBar(
        title = { TitleBar(name) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor
        ),
        navigationIcon = {
            if (onNavigationClick != null) {
                MainIconButton(
                    onClick = onNavigationClick,
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            if (onActionButtonClick != null){
                MainIconButton(
                    onClick = onActionButtonClick,
                    icon = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    )
}