package com.example.ghibliapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ghibliapp.components.BottomNavigationBar
import com.example.ghibliapp.components.CenterAppBar
import com.example.ghibliapp.components.FilmCard
import com.example.ghibliapp.utils.Constants
import com.example.ghibliapp.viewmodels.FilmViewModel

@Composable
fun HomeView(viewModel: FilmViewModel, navController: NavController) {
    val films by viewModel.films.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val favoriteIds = remember(favorites) {
        favorites.map { it.id }.toSet()
    }

    Scaffold(
        topBar = {
            CenterAppBar(
                name = "Studio Ghibli",
                containerColor = MaterialTheme.colorScheme.surface
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (films.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("No films available", color = MaterialTheme.colorScheme.onSurface)
                    }


                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(films) { film ->
                            FilmCard(
                                film = film,
                                isFavorite = favoriteIds.contains(film.id),
                                onClick = {
                                    navController.navigate("${Constants.DETAILS_VIEW}/${film.id}")
                                },
                                onFavoriteClick = {
                                    viewModel.toggleFavorite(film.copy(isFavorite = favoriteIds.contains(film.id)))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}