package com.example.ghibliapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ghibliapp.utils.Constants

/***
 * Project: Games App
 * Package: com.example.gamesapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 21:56
 * All rights reserved 2025.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchGameView(viewModel: GamesViewModel, navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(true) }
    val gamesSearched by viewModel.gamesSearched.collectAsState()
    DisposableEffect(Unit) {
        onDispose {
            viewModel.cleanGamesSearched()
        }
    }
    Surface(color = MaterialTheme.colorScheme.surface) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            query = query,
            onQueryChange = {
                query = it
                viewModel.searchGames(query)
            },
            onSearch = {
                keyboardController?.hide()
            },
            active = active,
            onActiveChange = {
                active = it
                if (!it){
                    navController.popBackStack()
                }
            },
            placeholder = { Text("Search...") },
            leadingIcon = {
                Icon(Icons.Default.Search, "Search...")
            },
            trailingIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBackIos, "Back")
                }
            },
            enabled = true,
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface,
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 10.dp)
            ) {
                items(gamesSearched) {
                    Text(
                        it.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp)
                            .clickable {
                                navController
                                    .navigate(Constants.DETAILS_VIEW + "/${it.id}")
                            }
                    )
                }
            }

        }
    }
}