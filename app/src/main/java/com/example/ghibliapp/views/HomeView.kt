package com.example.ghibliapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.ghibliapp.components.CardGame
import com.example.ghibliapp.components.CenterAppBar
import com.example.ghibliapp.components.Loader
import com.example.ghibliapp.utils.Constants

/***
 * Project: Games App
 * Package: com.example.gamesapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 16:41
 * All rights reserved 2025.
 **/

@Composable
fun HomeView(viewModel: GamesViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAppBar(
                name = "Games App",
                containerColor = MaterialTheme.colorScheme.surface,
                onActionButtonClick = {
                    navController.navigate(Constants.SEARCH_VIEW)
                }
            )
        }
    ) {
        ContentHomeView(viewModel, it, navController)
    }
}

@Composable
fun ContentHomeView(
    viewModel: GamesViewModel,
    paddingValues: PaddingValues,
    navController: NavController
) {
    val gamesPage = viewModel.gamesPage.collectAsLazyPagingItems()
    val loadState = gamesPage.loadState
    when (loadState.refresh) {
        is LoadState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader()
            }
        }
        is LoadState.Error -> {
            Text("Error al cargar los juegos.")
        }
        is LoadState.NotLoading -> {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(gamesPage.itemCount) { index ->
                    val item = gamesPage[index]
                    if (item != null) {
                        CardGame(item) {
                            navController.navigate(Constants.DETAILS_VIEW + "/${item.id}")
                        }
                        Text(
                            item.name,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .height(50.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Loader()
                            }
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            Text("Error cargando más juegos.")
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}