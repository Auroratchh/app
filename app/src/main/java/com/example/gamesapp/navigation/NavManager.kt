package com.example.gamesapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamesapp.utils.Constants
import com.example.gamesapp.viewmodels.GamesViewModel
import com.example.gamesapp.views.DetailsView
import com.example.gamesapp.views.HomeView
import com.example.gamesapp.views.SearchGameView

/***
 * Project: Games App
 * Package: com.example.gamesapp.navigation
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 17:26
 * All rights reserved 2025.
 **/

@Composable
fun NavManager(gamesViewModel: GamesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Constants.HOME_ROUTE){
        composable(Constants.HOME_ROUTE) {
            HomeView(gamesViewModel, navController)
        }
        composable(Constants.DETAILS_VIEW + "/{id}", arguments = listOf(
            navArgument("id"){type = NavType.LongType}
        )) {
            val id = it.arguments?.getLong(("id")) ?: 0
            DetailsView(gamesViewModel, navController, id)
        }
        composable(Constants.SEARCH_VIEW) {
            SearchGameView(gamesViewModel, navController)
        }
    }
}