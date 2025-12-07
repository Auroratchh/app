package com.example.gamesapp.repositories

import com.example.gamesapp.data.ApiGames
import com.example.gamesapp.models.GameModel
import com.example.gamesapp.models.GamesModel
import kotlinx.coroutines.delay
import javax.inject.Inject

/***
 * Project: Games App
 * Package: com.example.gamesapp.repositories
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 16:32
 * All rights reserved 2025.
 **/
class GamesRepository @Inject constructor(private val apiGames: ApiGames) {
    suspend fun getGames(): List<GameModel>? {
        val response = apiGames.getGames()
        if (response.isSuccessful)
            return response.body()?.results
        return null
    }

    suspend fun getGamesPaginated(page: Int, pageSize: Int): List<GameModel>? {
        delay(3000L)
        val response = apiGames.getGamesPaging(page, pageSize);
        if (response.isSuccessful)
            return  response.body()?.results
        return  emptyList()
    }

    suspend fun getGameById(id: Int): GameModel?{
        val response = apiGames.getGameById(id)
        if (response.isSuccessful)
            return response.body()
        return null
    }

    suspend fun getGamesByName(name: String): List<GameModel>? {
        val response = apiGames.searchGames(name)
        if (response.isSuccessful)
            return response.body()?.results
        return null
    }
}