package com.example.gamesapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamesapp.components.CenterAppBar
import com.example.gamesapp.components.MainImage
import com.example.gamesapp.components.MetaWebsite
import com.example.gamesapp.components.ReviewCard
import com.example.gamesapp.utils.Constants
import com.example.gamesapp.viewmodels.GamesViewModel

/***
 * Project: Games App
 * Package: com.example.gamesapp.views
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:30
 * All rights reserved 2025.
 **/

@Composable
fun DetailsView(viewModel: GamesViewModel, navController: NavController, id: Long) {
    val isLoading by viewModel.isGameLoading.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getGameById(id.toInt())
    }
    if (!isLoading) {
        Scaffold(
            topBar = {
                CenterAppBar(
                    name = viewModel.state.name,
                    containerColor = MaterialTheme.colorScheme.surface,
                    onNavigationClick = {
                        navController.navigate(Constants.HOME_ROUTE)
                    },
                    onActionButtonClick = {
                        navController.navigate(Constants.SEARCH_VIEW)
                    }
                )
            },
        ) {
            ContentDetailsView(it, viewModel)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ContentDetailsView(
    paddingValues: PaddingValues,
    viewModel: GamesViewModel,
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        MainImage(imageUrl = state.backgroundImage)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 5.dp)
        ) {
            MetaWebsite(state.website)
            ReviewCard(state.metacritic.toInt())
        }
        val scroll = rememberScrollState(0)
        Text(
            state.description,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                .verticalScroll(scroll)
        )
    }
}