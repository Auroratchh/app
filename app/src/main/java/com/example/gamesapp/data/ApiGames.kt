package com.example.gamesapp.data

import com.example.gamesapp.models.GameModel
import com.example.gamesapp.models.GamesModel
import com.example.gamesapp.utils.Constants.Companion.API_KEY
import com.example.gamesapp.utils.Constants.Companion.GAMEST_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
 * Project: Games App
 * Package: com.example.gamesapp.data
 * Created by Kevin Daniel Flores Nataren
 * File created at 04/abril/2025 at 16:59
 * All rights reserved 2025.
 **/
interface ApiGames {
    @GET(GAMEST_ENDPOINT + API_KEY)
    suspend fun getGames(): Response<GamesModel>

    @GET(GAMEST_ENDPOINT + API_KEY)
    suspend fun getGamesPaging(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Response<GamesModel>

    @GET("$GAMEST_ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id") id: Int): Response<GameModel>

    @GET("$GAMEST_ENDPOINT$API_KEY")
    suspend fun searchGames(
        @Query("search") name: String
    ): Response<GamesModel>
}