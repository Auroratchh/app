package com.example.gamesapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gamesapp.data.GamesDataSource
import com.example.gamesapp.models.GameModel
import com.example.gamesapp.repositories.GamesRepository
import com.example.gamesapp.state.GameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * Project: Games App
 * Package: com.example.gamesapp.viewmodels
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 16:35
 * All rights reserved 2025.
 **/
@HiltViewModel
class GamesViewModel @Inject constructor(private val repository: GamesRepository):
    ViewModel()
{
    var gamesSearched: MutableStateFlow<List<GameModel>> = MutableStateFlow(emptyList())
        private set

    var state by mutableStateOf(GameState())
        private set

    var isGamesLoading = MutableStateFlow<Boolean>(false)
        private set

    var isGameLoading = MutableStateFlow<Boolean>(false)
        private set

    val gamesPage = Pager(PagingConfig(pageSize = 3)){
        GamesDataSource(repository)
    }.flow.cachedIn(viewModelScope)

    fun searchGames(name: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                isGamesLoading.value = true
                gamesSearched.value = repository.getGamesByName(name) ?: emptyList()
                isGamesLoading.value = false
            }
        }
    }

    fun cleanGamesSearched(){
        gamesSearched.value = emptyList()
    }

    fun getGameById(id: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                isGameLoading.value = true
                val result = repository.getGameById(id)
                state = state.copy(
                    name = result?.name ?: "",
                    description = result?.description ?: "",
                    metacritic = result?.metacritic ?: 111,
                    website = result?.website ?: "Sin web",
                    backgroundImage = result?.backgroundImage ?: ""
                )
                isGameLoading.value = false
            }
        }
    }
}