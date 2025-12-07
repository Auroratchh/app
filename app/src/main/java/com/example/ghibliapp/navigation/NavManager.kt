package com.example.ghibliapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ghibliapp.utils.Constants
import com.example.ghibliapp.viewmodels.FilmViewModel
import com.example.ghibliapp.views.DetailsView
import com.example.ghibliapp.views.FavoritesView
import com.example.ghibliapp.views.HomeView
import com.example.ghibliapp.views.SearchView

@Composable
fun NavManager(filmViewModel: FilmViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Constants.HOME_ROUTE) {
        composable(Constants.HOME_ROUTE) {
            HomeView(filmViewModel, navController)
        }

        composable(Constants.SEARCH_VIEW) {
            SearchView(filmViewModel, navController)
        }

        composable(Constants.FAVORITES_VIEW) {
            FavoritesView(filmViewModel, navController)
        }

        composable(
            "${Constants.DETAILS_VIEW}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetailsView(filmViewModel, navController, id)
        }
    }
}